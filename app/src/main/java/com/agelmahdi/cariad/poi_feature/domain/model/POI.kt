package com.agelmahdi.cariad.poi_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class POI(
    @PrimaryKey val id: Int? = null,
    val address: Address? = null,
    val operatorInfo: Operator? = null,
    val numberOfPoints: Int? = 0,
)
