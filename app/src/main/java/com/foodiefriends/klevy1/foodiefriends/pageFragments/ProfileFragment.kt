package com.foodiefriends.klevy1.foodiefriends.pageFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.foodiefriends.klevy1.foodiefriends.R
import com.foodiefriends.klevy1.foodiefriends.dataModels.Profile

private const val PROFILE_ID = "param1"

class ProfileFragment : Fragment() {
    private var profileId: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            profileId = it.getString(PROFILE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        with(getProfile()) {
            view.findViewById<TextView>(R.id.profileName).text = name
            view.findViewById<TextView>(R.id.finds).text = getFindsCount()
            view.findViewById<TextView>(R.id.followers).text = getFollowersCount()
            view.findViewById<TextView>(R.id.following).text = getFollowingCount()
        }
        return view
    }

    private fun getProfile() : Profile {
        return Profile("Jane", 5, listOf(), listOf())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        @JvmStatic
        fun newInstance(profileId: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(PROFILE_ID, profileId)
                    }
                }
    }
}
