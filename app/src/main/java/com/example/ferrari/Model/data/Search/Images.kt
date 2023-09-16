package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("default")
    val default: String,
    @SerializedName("overflow")
    val overflow: String
)