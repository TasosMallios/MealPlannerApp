package com.example.mealplannerfinalproject.domain.use_case

// Home use cases wrapper
data class HomeUseCases(
    val getWeeklyMeals: GetWeeklyMealsUseCase,
    val toggleMealLike: ToggleMealLikeUseCase,
    val toggleMealSelection: ToggleMealSelectionUseCase
)
