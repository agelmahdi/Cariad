package com.agelmahdi.cariad.poi_feature.domain.use_case

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import kotlinx.coroutines.flow.Flow

class GetPoiUseCase(
    private val repository: POIRepository,
) : PoiUseCase {
    override suspend fun fetch(
    ): Flow<Resource<List<POI>>> {
        // all data validation supposed to be handled here
        return repository.getPOIData()
    }
}