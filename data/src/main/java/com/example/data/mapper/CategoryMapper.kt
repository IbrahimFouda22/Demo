package com.example.data.mapper

import com.example.data.dto.CategoryDto
import com.example.domain.entity.Category


fun CategoryDto.toEntity():List<Category>{
    return data.data.map {
        Category(
            id = it.id,
            name = it.name,
            nameAr = it.name_ar,
            nameEn = it.name_en,
            image = it.image
        )
    }
}