package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("actions")
    val actions: List<ActionXX>,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("type")
    val type: String
)