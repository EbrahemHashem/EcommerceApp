package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("product")
    val product: ProductX? = ProductX()
)