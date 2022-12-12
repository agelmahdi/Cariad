package com.agelmahdi.cariad.poi_feature.domain.repository


import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import retrofit2.Response

class FakePOIRepository(private val api: POIsAPI): POIRepository{
    override suspend fun getPOIsData(): Response<List<POIDTO>> {
        return Response.success(api.getPOIsInfo().body())
    }
}