package com.example.mealplannerfinalproject.domain.use_case

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.model.MealWeek
import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import javax.inject.Inject

class GetWeeklyMealsUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(week: String): NetworkResult<MealWeek> {
        return mealsRepository.getWeeklyMeals(week)
    }
}