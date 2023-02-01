package com.agelmahdi.cariad.poi_feature.data.remote.dto

import com.agelmahdi.cariad.poi_feature.domain.model.Address
import com.agelmahdi.cariad.poi_feature.domain.model.Operator
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.google.gson.annotations.SerializedName

data class POIDTO(
    @SerializedName("AddressInfo")
    val address: AddressDTO?,
    @SerializedName("OperatorInfo")
    val operator: OperatorDTO?,
    @SerializedName("NumberOfPoints")
    val numberOfPoints: Int?

) {
    fun toPOIs(): POI {
        return POI(
            address = address?.toAddress() ?: Address(
                "Not found",
                "Not found",
                "Not found",
                0.0,
                0.0
            ),
            operatorInfo = operator?.toOperatorInfo() ?: Operator("Not Found"),

            numberOfPoints = numberOfPoints ?: 0,
        )
    }


}