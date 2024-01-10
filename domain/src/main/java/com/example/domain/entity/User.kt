package com.example.domain.entity

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val type: Int,
    val status: Int,
    val balance: String?,
    val image: String,
    val token: String,
    val message: String,
    val success: Boolean,
    val addresses: List<Address>
)
