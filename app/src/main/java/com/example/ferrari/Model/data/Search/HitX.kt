package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class HitX(
    @SerializedName("snippet")
    val snippet: String,
    @SerializedName("track")
    val track: Track
)