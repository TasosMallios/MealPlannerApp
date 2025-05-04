package com.example.mealplannerfinalproject.data.model.requests

import com.google.gson.annotations.SerializedName

// Data class for user registration requests
data class RegisterRequestDTO(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)