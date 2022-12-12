package com.agelmahdi.cariad.poi_feature.domain.use_case

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import kotlinx.coroutines.flow.Flow

interface PoiUseCase {
    fun fetch(): Flow<Resource<List<POI>>>
}