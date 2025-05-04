package com.example.mealplannerfinalproject.ui.model

data class MealWeekUI (
    val week: String? = null,
    val days: List<MealDayUI> = emptyList(),
    val hasNext: Boolean = false,
    val hasPrevious: Boolean = false
)