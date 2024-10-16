package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    @SerializedName("price")
    val price: Int? = null
)