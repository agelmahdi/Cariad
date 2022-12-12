package com.agelmahdi.cariad.poi_feature.util

import com.agelmahdi.cariad.poi_feature.data.remote.dto.AddressDTO
import com.agelmahdi.cariad.poi_feature.data.remote.dto.OperatorDTO
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

object utils {

    class MockResponse<T>() {
        val errorResponse = Response.error<T>(
            400,
            ResponseBody.create(MediaType.parse("application/json"), "")
        )

        fun mockErrorResponse(): Response<T> {
            return errorResponse
        }
    }

    fun mockPOIResponse(): Response<List<POIDTO>> {
        return Response.success(mockPOI())
    }

    fun mockPOI(): List<POIDTO> {
        val poiDTO = POIDTO(
            address = AddressDTO(
                title = "title",
                addressLine1 = "addressLine1",
                addressLine2 = "addressLine2",
                latitude = 0.0,
                longitude = 0.0
            ),
            operatorInfo = OperatorDTO(
                title = "title"
            ),
            numberOfPoints = 0
        )

        return listOf(poiDTO)
    }
}

