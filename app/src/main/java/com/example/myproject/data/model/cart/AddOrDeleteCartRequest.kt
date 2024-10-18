package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class AddOrDeleteCartRequest(
    @SerializedName("product_id")
    val productId: Int? = null
)