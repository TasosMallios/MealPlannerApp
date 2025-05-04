package com.example.mealplannerfinalproject.data.model.requests

import com.google.gson.annotations.SerializedName

// Data class for username and password requests
data class LoginRequestDTO (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)