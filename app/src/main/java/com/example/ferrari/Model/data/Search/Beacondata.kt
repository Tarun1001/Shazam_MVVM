package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Beacondata(
    @SerializedName("providername")
    val providername: String,
    @SerializedName("type")
    val type: String
)