package com.agelmahdi.cariad.poi_feature.data.remote.dto

import com.agelmahdi.cariad.poi_feature.domain.model.Operator
import com.google.gson.annotations.SerializedName

data class OperatorDTO(
    @SerializedName("Title")
    val title: String?,
) {
    fun toOperatorInfo(): Operator {
        return Operator(
            title = title
        )
    }
}
