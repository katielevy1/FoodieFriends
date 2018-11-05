package com.foodiefriends.klevy1.foodiefriends.Fragments

import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.foodiefriends.klevy1.foodiefriends.R
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.restaurant_list_fragment.*
import kotlinx.android.synthetic.main.restaurant_list_fragment.view.*

class RestaurantListFragment : Fragment(), OnMapReadyCallback {
    private var showListView = true
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewManager = LinearLayoutManager(activity)
        val data = listOf("Main Restaurant 1", "Main Restaurant 2", "Main Restaurant 3",
                "Main Restaurant 4", "Main Restaurant 5", "Main Restaurant 6", "Main Restaurant 7").toTypedArray()
        val viewAdapter = MyAdapter(data)
        val view = inflater.inflate(R.layout.restaurant_list_fragment, container, false)

        val recyclerView = view.restaurant_list_recycler_view
        recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
        }
        if (!showListView) {
            restaurant_list_recycler_view.visibility = View.GONE
        }
        setupListenerForViewToggle(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = SupportMapFragment()
        childFragmentManager.beginTransaction().replace(R.id.restaurant_list_map_view_holder, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }

    private fun setupListenerForViewToggle(myView : View) {
        myView.list_toggle.setOnClickListener {
            if (!showListView) {
                showList()
                showListView = !showListView
            }
        }
        myView.map_toggle.setOnClickListener {
            if (showListView) {
                showMap()
                showListView = !showListView
            }
        }
    }

    private fun showMap() {
        restaurant_list_recycler_view.visibility = View.GONE
        restaurant_list_map_view_holder.visibility = View.VISIBLE
        list_toggle.setTypeface(null, Typeface.NORMAL)
        map_toggle.setTypeface(null, Typeface.BOLD_ITALIC)
    }

    private fun showList() {
        restaurant_list_recycler_view.visibility = View.VISIBLE
        restaurant_list_map_view_holder.visibility = View.GONE
        list_toggle.setTypeface(null, Typeface.BOLD_ITALIC)
        map_toggle.setTypeface(null, Typeface.NORMAL)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val geoDataClient: GeoDataClient = context?.let { Places.getGeoDataClient(it) } ?: return
        val nobu = "5581544c6b78ffe1f9a37c00bf27c435c18b8990"
//        geoDataClient.getPlaceById(nobu).addOnCompleteListener { task ->
//            if(task.isSuccessful) {
//                val places = task.result
//                val myPlace = places?.get(0)
//                Log.i(GooglePlaces.TAG, "Place found: " + myPlace?.name)
//                val latLng = myPlace?.latLng
//                latLng?.let {
//                    mMap.addMarker(MarkerOptions().position(latLng).title(myPlace.name.toString()))
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
//                }
//                places?.release()
//
//            } else {
//                Log.e(GooglePlaces.TAG, "Place not found.")
//            }
//        }



        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}

class MyAdapter(private val myDataset: Array<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: ConstraintLayout) : RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.restaurant_item, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.findViewById<TextView>(R.id.restaurant_name).text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}