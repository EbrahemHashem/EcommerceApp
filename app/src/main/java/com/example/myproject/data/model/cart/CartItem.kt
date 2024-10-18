package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("product")
    val product: ProductX? = ProductX(),
    @SerializedName("quantity")
    val quantity: Int? = 0
)