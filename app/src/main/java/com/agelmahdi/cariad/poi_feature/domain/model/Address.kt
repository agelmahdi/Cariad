package com.agelmahdi.cariad.poi_feature.domain.model


data class Address(
    val title: String? = "",
    val addressLine1: String? = "",
    val addressLine2: String? = "",
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0
)
