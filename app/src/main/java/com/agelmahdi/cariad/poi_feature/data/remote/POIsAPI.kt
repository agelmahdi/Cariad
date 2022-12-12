package com.agelmahdi.cariad.poi_feature.data.remote

import com.agelmahdi.cariad.BuildConfig
import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import com.agelmahdi.cariad.poi_feature.util.Constance.DISTANCE
import com.agelmahdi.cariad.poi_feature.util.Constance.LATITUDE
import com.agelmahdi.cariad.poi_feature.util.Constance.LONGITUDE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface POIsAPI {
    @GET("v3/poi")
    suspend fun getPOIsInfo(
        @Query("key") key:String = BuildConfig.OPEN_MAP_KEY,
        @Query("distance") distance: Double = DISTANCE,
        @Query("latitude") lat:Double = LATITUDE,
        @Query("longitude") lng: Double = LONGITUDE,
    ): Response<List<POIDTO>>
}