package com.example.data.mapper

import com.example.data.dto.LoginDto
import com.example.domain.entity.Address
import com.example.domain.entity.User

fun LoginDto.toEntity(): User{
    return User(
        id = data.id,
        image = data.image,
        name = data.name,
        email = data.email,
        phone = data.phone,
        type = data.type,
        status = data.status,
        balance = data.balance,
        token = data.token,
        message = message,
        success = success,
        addresses = data.addresses.map {
            Address(
                id = it.id,
                building = it.building,
                lat = it.lat,
                lng = it.lng,
                address = it.address,
                street = it.street,
                apartment = it.apartment,
                floor = it.floor,
                name = it.name,
            )
        }
    )
}