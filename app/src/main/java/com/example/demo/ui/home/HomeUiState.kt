package com.example.demo.ui.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryUiState: List<CategoryUiState> = arrayListOf(),
    val popularUiState:  List<PopularUiState> = arrayListOf(),
    val trendingUiState: List<TrendingUiState> = arrayListOf()
)

data class CategoryUiState(
    val id:Int,
    val image:String,
    val name:String,
    val nameAr:String,
    val nameEn:String,
)

data class PopularUiState(
    val id: Int,
    val name: String,
    val email: String,
    val image: String,
    val address: String?,
    val distance: Int,
    val rate: Int,
    val isFavorite: Boolean,
)

data class TrendingUiState(
    val logo: String,
)
