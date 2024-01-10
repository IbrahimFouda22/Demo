package com.example.data.dto

data class LoginDto(
    val message: String,
    val success: Boolean,
    val data: AuthDtoData
)

data class AuthDtoData(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val type: Int,
    val status: Int,
    val balance: String?,
    val image: String,
    val token: String,
    val addresses: List<AddressDto>,
)
