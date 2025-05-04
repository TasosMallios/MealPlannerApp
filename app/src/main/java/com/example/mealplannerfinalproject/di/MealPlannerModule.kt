package com.example.mealplannerfinalproject.di

import com.example.mealplannerfinalproject.data.service.MealPlannerAPI
import com.example.mealplannerfinalproject.data.repository.MealsRepositoryImpl
import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MealPlannerModule {
    @Provides
    fun provideMealsRepository(mealPlannerAPI: MealPlannerAPI): MealsRepository {
        return MealsRepositoryImpl(mealPlannerAPI)
    }
}