package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("hits")
    val hits: List<Hit>
)