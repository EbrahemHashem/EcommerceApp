package com.example.myproject.data.model.cart


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("images")
    val images: List<String?>? = null,
    @SerializedName("in_cart")
    val inCart: Boolean? = null,
    @SerializedName("in_favorites")
    val inFavorites: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    @SerializedName("price")
    val price: Int? = null
)