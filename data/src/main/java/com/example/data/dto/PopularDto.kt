package com.example.data.dto

data class PopularDto(
    val success: Boolean,
    val data: List<PopularDtoData>,
)

data class PopularDtoData(
    val id: Int,
    val name: String,
    val email: String,
    val image: String,
    val address: String?,
    val distance: Int,
    val rate: Int,
    val is_favorite: Boolean,
)