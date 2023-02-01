package com.agelmahdi.cariad.poi_feature.data.repository_impl

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.data.local.POIDatabase
import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import com.agelmahdi.cariad.poi_feature.util.Massages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class POIRepositoryImpl(
    private val api: POIsAPI,
    private val poiDatabase: POIDatabase
) : POIRepository {
    override suspend fun getPOIData(): Flow<Resource<List<POI>>> = flow {
        val cachedPOIData = poiDatabase.poiDAO.getALlPOIs()
        emit(Resource.Loading(cachedPOIData))
        try {
            if (api.getPOIsInfo().isSuccessful) {
                val result = api.getPOIsInfo().body() as List<POIDTO>

                poiDatabase.poiDAO.deleteAll()
                result.forEach {
                    poiDatabase.poiDAO.insertOrUpdatePOI(it.toPOIs())
                }
                emit(Resource.Success(result.map { it.toPOIs() }))

            } else {
                emit(Resource.Error(Massages.UNSUCCESSFUL_API,
                    cachedPOIData))
            }

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "${Massages.IO_EXCEPTION}: ${e.message}",
                    cachedPOIData
                )
            )
        } catch (e: TimeoutException) {
            emit(
                Resource.Error(
                    "${Massages.TIMEOUT_EXCEPTION}: ${e.message}",
                    cachedPOIData
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "${Massages.HTTP_EXCEPTION}: ${e.message}",
                    cachedPOIData
                )
            )
        }
    }
}
