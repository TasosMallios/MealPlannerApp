package com.example.mealplannerfinalproject.domain.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.model.Meal
import com.example.mealplannerfinalproject.domain.model.MealWeek

// Interface for the meals repository
interface MealsRepository {
    suspend fun getWeeklyMeals(week: String): NetworkResult<MealWeek>

    suspend fun selectMeal(
        week: String,
        date: String,
        mealId: Int?,
    ): Meal

    suspend fun deselectMeal(
        week: String,
        date: String,
        mealId: Int?,
    ): Meal

    suspend fun mealLike(
        mealId: Int?,
    ): Meal

    suspend fun mealUnlike(
        mealId: Int?,
    ): Meal
}