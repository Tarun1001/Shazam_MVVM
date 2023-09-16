package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("hits")
    val hits: List<HitX>
)