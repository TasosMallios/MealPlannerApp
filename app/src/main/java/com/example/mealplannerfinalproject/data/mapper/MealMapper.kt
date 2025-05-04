package com.example.mealplannerfinalproject.data.mapper

import com.example.mealplannerfinalproject.data.model.MealDTO
import com.example.mealplannerfinalproject.domain.model.Meal

fun MealDTO.toDomain(): Meal {
    return Meal(
        id = this.id ?: 0,
        name = name.orEmpty(),
        description = description.orEmpty(),
        type = type.orEmpty(),
        calories = calories ?: 0,
        selected = selected ?: false,
        liked = liked ?: false
    )
}