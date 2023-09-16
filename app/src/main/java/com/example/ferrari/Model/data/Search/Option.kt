package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("actions")
    val actions: List<ActionX>,
    @SerializedName("beacondata")
    val beacondata: Beacondata,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("colouroverflowimage")
    val colouroverflowimage: Boolean,
    @SerializedName("image")
    val image: String,
    @SerializedName("listcaption")
    val listcaption: String,
    @SerializedName("overflowimage")
    val overflowimage: String,
    @SerializedName("providername")
    val providername: String,
    @SerializedName("type")
    val type: String
)