package com.example.data.dto

data class AddressDto(
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
