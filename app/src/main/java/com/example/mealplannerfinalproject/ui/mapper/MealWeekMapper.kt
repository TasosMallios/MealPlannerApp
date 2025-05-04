package com.example.mealplannerfinalproject.ui.mapper

import com.example.mealplannerfinalproject.domain.model.MealWeek
import com.example.mealplannerfinalproject.ui.model.MealWeekUI

fun MealWeek.toUI(): MealWeekUI {
    return MealWeekUI(
        week = this.week,
        days = this.days.map { it.toUI() },
        hasNext = this.hasNext,
        hasPrevious = this.hasPrevious
    )
}