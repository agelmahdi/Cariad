package com.agelmahdi.cariad.poi_feature.data.repository_impl

import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import retrofit2.Response

class POIRepositoryImpl(
    private val api: POIsAPI
) : POIRepository {
    override suspend fun getPOIsData(): Response<List<POIDTO>> = api.getPOIsInfo()
}
