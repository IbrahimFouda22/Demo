package com.example.data.dto

data class CategoryDto(
    val success: Boolean,
    val data: CategoryDtoData
)
data class CategoryDtoData(
    val data: List<CategoryDtoDetailsData>,
    val cart_count: Int
)

data class CategoryDtoDetailsData(
    val id: Int,
    val name: String,
    val image: String,
    val active: Int,
    val name_ar: String,
    val name_en: String,
)