package com.example.domain.entity

data class Address(
    val id: Int,
    val lat: Double,
    val lng: Double,
    val address: String?,
    val street: String?,
    val building: String?,
    val apartment: String?,
    val floor: String?,
    val name: String?,
)
