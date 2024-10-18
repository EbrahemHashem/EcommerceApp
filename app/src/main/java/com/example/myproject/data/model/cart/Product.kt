package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    @SerializedName("price")
    val price: Int? = null
)