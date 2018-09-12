package com.foodiefriends.klevy1.foodiefriends

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RestaurantListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {
        return inflater.inflate(R.layout.restaurant_list_fragment, container, false)
    }
}