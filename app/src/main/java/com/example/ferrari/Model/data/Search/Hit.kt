package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("artist")
    val artist: Artist
)