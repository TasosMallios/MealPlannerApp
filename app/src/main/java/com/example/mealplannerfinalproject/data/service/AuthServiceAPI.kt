package com.example.mealplannerfinalproject.data.service

import com.example.mealplannerfinalproject.data.model.LoginDTO
import com.example.mealplannerfinalproject.data.model.requests.LoginRequestDTO
import com.example.mealplannerfinalproject.data.model.RegisterDTO
import com.example.mealplannerfinalproject.data.model.requests.RegisterRequestDTO
import com.example.mealplannerfinalproject.utils.Constants.BASE_MEALS_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceAPI {
    // Endpoint for user login
    @POST("${BASE_MEALS_URL}/api/auth/sessions")
    suspend fun loginUser(
        @Body loginRequest: LoginRequestDTO
    ): Response<LoginDTO>


    // Endpoint for user registration
    @POST("${BASE_MEALS_URL}/api/users/")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequestDTO
    ): Response<RegisterDTO>
}