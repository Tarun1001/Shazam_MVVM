package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("adamid")
    val adamid: String,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("verified")
    val verified: Boolean,
    @SerializedName("weburl")
    val weburl: String
)