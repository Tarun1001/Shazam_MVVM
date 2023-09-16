package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class ActionXX(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)