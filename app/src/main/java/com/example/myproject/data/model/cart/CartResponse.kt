package com.example.myproject.data.model.cart


import com.example.myproject.data.model.DataX
import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("data")
    val `data`: DataCart? ,
    @SerializedName("message")
    val message: Any? = Any(),
    @SerializedName("status")
    val status: Boolean? = false
)