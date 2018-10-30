package com.foodiefriends.klevy1.foodiefriends

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.tasks.OnCompleteListener


class GooglePlaces {

    init {

    }

    val TAG = "GooglePlaces"
    fun getMarkers(context: Context, placeId: String): Set<LatLng> {
        val geoDataClient = Places.getGeoDataClient(context)
        val placeDetectionClient = Places.getPlaceDetectionClient(context)
        geoDataClient.getPlaceById(placeId).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val places = task.result
                val myPlace = places?.get(0)
                Log.i(TAG, "Place found: " + myPlace.getName());
                places.release();
            } else {
                Log.e(TAG, "Place not found.");
            }

        }
        return setOf()
    }


}