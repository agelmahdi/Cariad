package com.agelmahdi.cariad.poi_feature.domain.repository

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import kotlinx.coroutines.flow.Flow

import retrofit2.Response

interface POIRepository {
    suspend fun getPOIData(): Flow<Resource<List<POI>>>
}