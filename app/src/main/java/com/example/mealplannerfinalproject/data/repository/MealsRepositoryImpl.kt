package com.example.mealplannerfinalproject.data.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.mapper.toDomain
import com.example.mealplannerfinalproject.data.model.requests.LikeMealRequestDTO
import com.example.mealplannerfinalproject.data.model.requests.MealsRequestDTO
import com.example.mealplannerfinalproject.data.service.MealPlannerAPI
import com.example.mealplannerfinalproject.domain.model.Meal
import com.example.mealplannerfinalproject.domain.model.MealWeek
import com.example.mealplannerfinalproject.domain.repository.MealsRepository
import retrofit2.HttpException
import java.io.IOException

// Implementation for the MealsRepository interface
class MealsRepositoryImpl(val mealPlannerAPI: MealPlannerAPI) : MealsRepository {
    // Fetch weekly meals
    override suspend fun getWeeklyMeals(week: String): NetworkResult<MealWeek> {
        return try {
            val response = mealPlannerAPI.getWeeklyMeals(week)

            return if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(
                    response.body()!!.toDomain()
                )
            } else {
                NetworkResult.Error(message = response.message(), code = response.code())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(
                message = e.message(),
                code = e.code()
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                message = "Network error. Please check your internet connection."
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = "Unexpected error occurred."
            )
        }
    }

    // Make a meal selected
    override suspend fun selectMeal(week: String, date: String, mealId: Int?): Meal {
        val request = MealsRequestDTO(week = week, date = date, mealID = mealId.toString())
        return mealPlannerAPI.selectMeal(request).toDomain()
    }

    // Deselect a meal
    override suspend fun deselectMeal(week: String, date: String, mealId: Int?): Meal {
        val request = MealsRequestDTO(week = week, date = date, mealID = mealId.toString())
        return mealPlannerAPI.deselectMeal(request).toDomain()
    }

    // Like a meal
    override suspend fun mealLike(mealId: Int?): Meal {
        val request = LikeMealRequestDTO(mealID = mealId.toString())
        return mealPlannerAPI.mealLike(request).toDomain()
    }

    // Unlike a meal
    override suspend fun mealUnlike(mealId: Int?): Meal {
        val request = LikeMealRequestDTO(mealID = mealId.toString())
        return mealPlannerAPI.mealUnlike(request).toDomain()
    }

}