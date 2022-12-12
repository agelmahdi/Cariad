package com.agelmahdi.cariad.poi_feature.domain.use_case

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakePoiUseCase(): PoiUseCase {
    private val fakeFlow = MutableSharedFlow<Resource<List<POI>>>()
    suspend fun emit(value: Resource<List<POI>>) = fakeFlow.emit(value)
    override fun fetch(): Flow<Resource<List<POI>>> = fakeFlow
}