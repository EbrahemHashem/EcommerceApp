package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class FavouritesResponse(
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("message")
    val message: Any? = Any(),
    @SerializedName("status")
    val status: Boolean? = false
)