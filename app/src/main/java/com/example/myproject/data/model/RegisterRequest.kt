package com.example.myproject.data.model

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String,
    val phone: String
)