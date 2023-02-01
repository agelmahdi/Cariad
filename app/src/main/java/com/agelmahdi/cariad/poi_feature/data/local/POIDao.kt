package com.agelmahdi.cariad.poi_feature.data.local

import androidx.room.*
import com.agelmahdi.cariad.poi_feature.domain.model.POI


@Dao
interface POIDao {
    @Query("SELECT * FROM POI")
    suspend fun getALlPOIs(): List<POI>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePOI(poi: POI)

    @Query("DELETE FROM POI")
    fun deleteAll()
}