package com.example.domain.entity

data class Popular(
    val id: Int,
    val name: String,
    val email: String,
    val image: String,
    val address: String?,
    val distance: Int,
    val rate: Int,
    val is_favorite: Boolean,
)
