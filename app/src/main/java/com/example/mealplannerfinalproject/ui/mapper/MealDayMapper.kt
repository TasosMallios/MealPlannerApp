package com.example.mealplannerfinalproject.ui.mapper

import com.example.mealplannerfinalproject.domain.model.MealDay
import com.example.mealplannerfinalproject.ui.model.MealDayUI

fun MealDay.toUI(): MealDayUI {
    return MealDayUI(
        date = this.date,
        meals = this.meals.map { it.toUI() }
    )
}