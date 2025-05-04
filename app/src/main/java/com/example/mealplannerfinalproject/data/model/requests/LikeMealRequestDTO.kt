package com.example.mealplannerfinalproject.data.model.requests

import com.google.gson.annotations.SerializedName

// Data class for meal likes/unlikes requests
data class LikeMealRequestDTO(
    @SerializedName("meal_id")
    val mealID: String,
)