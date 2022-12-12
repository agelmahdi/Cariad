package com.agelmahdi.cariad.poi_feature.domain.model

data class POI(
    val address: Address? = null,
    val operatorInfo: Operator? =null,
    val numberOfPoints: Int? = 0
)