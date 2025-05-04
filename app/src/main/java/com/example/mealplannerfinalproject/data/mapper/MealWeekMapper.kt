package com.example.mealplannerfinalproject.data.mapper

import com.example.mealplannerfinalproject.data.model.MealWeekDTO
import com.example.mealplannerfinalproject.domain.model.MealWeek

fun MealWeekDTO.toDomain() : MealWeek {
    return MealWeek(
        week = week.orEmpty(),
        days = days?.map { it.toDomain() } ?: emptyList(),
        hasNext = hasNext ?: false,
        hasPrevious = hasPrevious ?: false
    )
}