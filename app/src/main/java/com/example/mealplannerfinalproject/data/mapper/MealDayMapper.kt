package com.example.mealplannerfinalproject.data.mapper

import com.example.mealplannerfinalproject.data.model.MealDayDTO
import com.example.mealplannerfinalproject.domain.model.MealDay

fun MealDayDTO.toDomain(): MealDay {
    return MealDay(
        date = date.orEmpty(),
        meals = meals?.map { it.toDomain() } ?: emptyList()
    )
}