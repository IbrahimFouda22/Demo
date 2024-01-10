package com.example.data.dto

data class TrendingDto(
    val success: Boolean,
    val data: List<TrendingDtoData>,
)

data class TrendingDtoData(
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