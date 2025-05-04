package com.example.mealplannerfinalproject.domain.model

data class MealWeek(
    val week: String,
    val days: List<MealDay>,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
)
