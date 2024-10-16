package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class AddOrDeleteFavouriteRequest(
    @SerializedName("product_id")
    val productId: Int? = null
)