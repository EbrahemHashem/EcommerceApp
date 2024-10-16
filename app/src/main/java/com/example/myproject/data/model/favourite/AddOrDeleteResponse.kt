package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class AddOrDeleteResponse(
    @SerializedName("data")
    val `data`: DataXX? = DataXX(),
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("status")
    val status: Boolean? = false
)