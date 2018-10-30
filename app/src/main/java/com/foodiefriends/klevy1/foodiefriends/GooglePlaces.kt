package com.foodiefriends.klevy1.foodiefriends

import android.content.Context
import android.util.Log
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.PlaceDetectionClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.places.Places


class GooglePlaces(context: Context) {

    private val geoDataClient: GeoDataClient = Places.getGeoDataClient(context)
    val placeDetectionClient: PlaceDetectionClient = Places.getPlaceDetectionClient(context)



//    fun getMarkerLocations(placeIds: Set<String>): Set<LatLng> {
//
//
//        return setOf()
//    }

    fun getLocation(placeId: String) {
        geoDataClient.getPlaceById(placeId).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val places = task.result
                val myPlace = places?.get(0)
                Log.i(TAG, "Place found: " + myPlace?.name)
                val latLng = myPlace?.latLng
                places?.release()
            } else {
                Log.e(TAG, "Place not found.")
            }
        }
    }

    companion object {
        const val TAG = "GooglePlaces"
    }

}