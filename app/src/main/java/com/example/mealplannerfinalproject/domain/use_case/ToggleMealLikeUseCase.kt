package com.example.mealplannerfinalproject.domain.use_case

import com.example.mealplannerfinalproject.domain.model.Meal
import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import javax.inject.Inject

class ToggleMealLikeUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(
        mealId: Int?,
        liked: Boolean
    ): Meal {
        return if (liked) {
            mealsRepository.mealLike(
                mealId = mealId
            )
        } else {
            mealsRepository.mealUnlike(
                mealId = mealId
            )
        }
    }
}