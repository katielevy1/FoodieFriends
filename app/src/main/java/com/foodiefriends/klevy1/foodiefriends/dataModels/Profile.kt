package com.foodiefriends.klevy1.foodiefriends.dataModels

class Profile (val name: String,
        val finds: Int,
        val followers: List<Profile>,
        val following: List<Profile>) {
    fun getFollowersCount() = "${followers.size}\n Followers"
    fun getFollowingCount() = "${followers.size}\n Following"
    fun getFindsCount() = "${finds}\n Finds"
}
