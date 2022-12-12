package com.agelmahdi.cariad.poi_feature.domain.model

data class POIsState(
    val data: List<POI>? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)