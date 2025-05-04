package com.example.mealplannerfinalproject.data.model

import com.google.gson.annotations.SerializedName

// Data class to get JWT token
data class LoginDTO(
    @SerializedName("token") val results: String? = null,
)
