package com.agelmahdi.cariad.poi_feature.data.remote.dto

import com.agelmahdi.cariad.poi_feature.domain.model.Address
import com.google.gson.annotations.SerializedName

data class AddressDTO(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("AddressLine1")
    val addressLine1: String?,
    @SerializedName("AddressLine2")
    val addressLine2: String?,
    @SerializedName("Latitude")
    val latitude: Double?,
    @SerializedName("Longitude")
    val longitude: Double?,
) {
    fun toAddress(): Address {
        return Address(
            title = title,
            addressLine1 = addressLine1,
            addressLine2 = addressLine2,
            latitude = latitude,
            longitude = longitude
        )
    }
}