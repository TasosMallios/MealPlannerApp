package com.example.mealplannerfinalproject.ui.mapper

import com.example.mealplannerfinalproject.domain.model.Meal
import com.example.mealplannerfinalproject.ui.model.MealUI

fun Meal.toUI(): MealUI {
    return MealUI(
        id = this.id,
        name = this.name,
        description = this.description,
        type = this.type,
        calories = this.calories,
        selected = this.selected,
        liked = this.liked
    )
}