package com.example.domain.entity

data class Trending(
    val id: Int,
    val name: String,
    val email: String,
    val image: String,
    val logo: String,
    val address: String?,
    val distance: Int,
    val rate: Int,
    val is_favorite: Boolean,
)
