package com.example.mealplannerfinalproject.domain.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.model.Register

// Interface for the register repository
interface RegisterRepository {
    suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): NetworkResult<Register>
}