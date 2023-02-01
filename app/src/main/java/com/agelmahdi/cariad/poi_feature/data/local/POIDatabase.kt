package com.agelmahdi.cariad.poi_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agelmahdi.cariad.poi_feature.domain.model.POI

@Database(
    entities = [POI::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class POIDatabase : RoomDatabase() {
    abstract val poiDAO: POIDao
}