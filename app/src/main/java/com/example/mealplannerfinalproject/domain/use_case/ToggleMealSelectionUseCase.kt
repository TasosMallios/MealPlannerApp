package com.example.mealplannerfinalproject.domain.use_case

import com.example.mealplannerfinalproject.domain.model.Meal
import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import javax.inject.Inject

class ToggleMealSelectionUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(
        week: String,
        date: String,
        mealId: Int?,
        selected: Boolean
    ): Meal {
        return if (selected) {
            mealsRepository.selectMeal(
                week = week,
                date = date,
                mealId = mealId
            )
        } else {
            mealsRepository.deselectMeal(
                week = week,
                date = date,
                mealId = mealId
            )
        }
    }
}