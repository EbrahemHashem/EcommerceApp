package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class AddorDeleteCartResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Boolean? = null
)