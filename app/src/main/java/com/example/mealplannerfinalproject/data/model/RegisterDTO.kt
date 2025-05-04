package com.example.mealplannerfinalproject.data.model

import com.google.gson.annotations.SerializedName

// Data class for registration
data class RegisterDTO(
    @SerializedName("success") val success: String? = null
)