package com.example.mealplannerfinalproject.di

import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import com.example.mealplannerfinalproject.domain.use_case.GetWeeklyMealsUseCase
import com.example.mealplannerfinalproject.domain.use_case.HomeUseCases
import com.example.mealplannerfinalproject.domain.use_case.ToggleMealLikeUseCase
import com.example.mealplannerfinalproject.domain.use_case.ToggleMealSelectionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideHomeUseCases(
        mealsRepository: MealsRepository
    ): HomeUseCases {
        return HomeUseCases(
            getWeeklyMeals = GetWeeklyMealsUseCase(mealsRepository),
            toggleMealSelection = ToggleMealSelectionUseCase(mealsRepository),
            toggleMealLike = ToggleMealLikeUseCase(mealsRepository)
        )
    }

}