package com.agelmahdi.cariad.poi_feature.domain.repository

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePOIRepository(private val api: POIsAPI) : POIRepository {
    override suspend fun getPOIData(): Flow<Resource<List<POI>>> = flow {
        emit(Resource.Success(api.getPOIsInfo().body()?.map { it.toPOIs() }))
    }
}