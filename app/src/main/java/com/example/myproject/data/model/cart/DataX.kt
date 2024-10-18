package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class DataCart(
    @SerializedName("cart_items")
    val cartItems: List<CartItem>? = listOf(),
    @SerializedName("sub_total")
    val subTotal: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)