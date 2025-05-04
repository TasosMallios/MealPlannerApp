package com.example.mealplannerfinalproject.domain.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.model.Login

// Interface for the login repository
interface LoginRepository {
    suspend fun loginUser(email: String, password: String): NetworkResult<Login>
}