package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product")
    val product: Product? = null,
    @SerializedName("quantity")
    val quantity: Int? = null
)