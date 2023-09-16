package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class SongSearch(
    @SerializedName("artists")
    val artists: Artists,
    @SerializedName("tracks")
    val tracks: Tracks
)