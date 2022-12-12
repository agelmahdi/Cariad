package com.agelmahdi.cariad.poi_feature.domain.use_case

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import com.agelmahdi.cariad.poi_feature.util.Massages.HTTP_EXCEPTION
import com.agelmahdi.cariad.poi_feature.util.Massages.IO_EXCEPTION
import com.agelmahdi.cariad.poi_feature.util.Massages.TIMEOUT_EXCEPTION
import com.agelmahdi.cariad.poi_feature.util.Massages.UNSUCCESSFUL_API
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class GetPoiUseCase(
    private val repository: POIRepository
) : PoiUseCase {
    override fun fetch(
    ): Flow<Resource<List<POI>>> = flow {
        emit(Resource.Loading())

        try {
            val remotePOIsInfo = repository.getPOIsData()
            if (remotePOIsInfo.isSuccessful) {
                val result = remotePOIsInfo.body() as List<POIDTO>
                emit(Resource.Success(result.map { it.toPOIs() }))
            } else {
                emit(Resource.Error(UNSUCCESSFUL_API))
            }

        } catch (e: IOException) {
            emit(Resource.Error("${IO_EXCEPTION}: ${e.message}"))
        } catch (e: TimeoutException) {
            emit(Resource.Error("${TIMEOUT_EXCEPTION}: ${e.message}"))
        } catch (e: HttpException) {
            emit(Resource.Error("${HTTP_EXCEPTION}: ${e.message}"))
        }
    }
}