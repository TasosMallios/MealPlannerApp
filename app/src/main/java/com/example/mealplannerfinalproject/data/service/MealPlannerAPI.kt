package com.example.mealplannerfinalproject.data.service

import com.example.mealplannerfinalproject.data.model.MealDTO
import com.example.mealplannerfinalproject.data.model.MealWeekDTO
import com.example.mealplannerfinalproject.data.model.requests.LikeMealRequestDTO
import com.example.mealplannerfinalproject.data.model.requests.MealsRequestDTO
import com.example.mealplannerfinalproject.utils.Constants.BASE_MEALS_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealPlannerAPI {

    // Endpoint for weekly meals fetching
    @GET("${BASE_MEALS_URL}/api/meals")
    suspend fun getWeeklyMeals(
        @Query("week") week: String
    ): Response<MealWeekDTO>

    // Endpoint for meal selection
    @POST("${BASE_MEALS_URL}/api/meals")
    suspend fun selectMeal(
        @Body mealsRequestDTO: MealsRequestDTO
    ) : MealDTO

    // Endpoint for meal deselection
    @POST("${BASE_MEALS_URL}/api/meals/unselect")
    suspend fun deselectMeal(
        @Body mealsRequestDTO: MealsRequestDTO
    ) : MealDTO

    // Endpoint for meal likes
    @POST("${BASE_MEALS_URL}/api/meals/like")
    suspend fun mealLike(
        @Body likeMealRequestDTO: LikeMealRequestDTO,
    ): MealDTO

    // Endpoint for meal unlikes
    @POST("${BASE_MEALS_URL}/api/meals/unlike")
    suspend fun mealUnlike(
        @Body likeMealRequestDTO: LikeMealRequestDTO,
    ): MealDTO
}