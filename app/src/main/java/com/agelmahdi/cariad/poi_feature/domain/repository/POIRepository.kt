package com.agelmahdi.cariad.poi_feature.domain.repository

import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO

import retrofit2.Response

interface POIRepository {
    suspend fun getPOIsData(): Response<List<POIDTO>>
}