package com.agelmahdi.cariad.poi_feature.util

import com.agelmahdi.cariad.poi_feature.util.Constance.ANCHOR
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

object Utils {

    fun createMarker(
        mMap: GoogleMap,
        latitude: Double,
        longitude: Double,
        title: String?,
        snippet: String?

    ): Marker? {
        return mMap.addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
                .anchor(ANCHOR, ANCHOR)
                .title(title)
                .snippet(snippet)
        )
    }

}