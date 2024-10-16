package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product")
    val product: Product? = null
)