package com.example.data.mapper

import com.example.data.dto.PopularDto
import com.example.domain.entity.Popular

fun PopularDto.toEntity(): List<Popular> {
    return data.map {
        Popular(
            id =it.id,
            name =it.name,
            email =it.email,
            image =it.image,
            address =it.address,
            distance =it.distance,
            rate =it.rate,
            is_favorite =it.is_favorite,
        )
    }
}