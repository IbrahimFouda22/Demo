package com.example.demo.ui.shared

data class UserUiState(
    val id: Int,
    val name: String,
    val token: String,
    val addresses: List<AddressUiState> = arrayListOf()
)

