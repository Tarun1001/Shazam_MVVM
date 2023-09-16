package com.example.ferrari.Model.data.Search


import com.google.gson.annotations.SerializedName

data class Hub(
    @SerializedName("actions")
    val actions: List<Action>,
    @SerializedName("displayname")
    val displayname: String,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("image")
    val image: String,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("providers")
    val providers: List<Provider>,
    @SerializedName("type")
    val type: String
)