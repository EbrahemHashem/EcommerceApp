package com.example.myproject.data.model.search

data class DataX(
    val description: String,
    val id: Int,
    val image: String,
    val images: List<String>,
    val in_cart: Boolean,
    val in_favorites: Boolean,
    val name: String,
    val price: Double
)