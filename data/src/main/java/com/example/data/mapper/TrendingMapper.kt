package com.example.data.mapper

import com.example.data.dto.TrendingDto
import com.example.domain.entity.Trending

fun TrendingDto.toEntity(): List<Trending> {
    return data.map {
        Trending(
            id =it.id,
            name =it.name,
            email =it.email,
            image =it.image,
            logo =it.logo,
            address =it.address,
            distance =it.distance,
            rate =it.rate,
            is_favorite =it.is_favorite,
        )
    }
}