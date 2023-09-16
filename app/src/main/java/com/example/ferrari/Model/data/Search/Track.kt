package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artists")
    val artists: List<ArtistX>,
    @SerializedName("hub")
    val hub: Hub,
    @SerializedName("images")
    val images: ImagesX,
    @SerializedName("key")
    val key: String,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("share")
    val share: Share,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)