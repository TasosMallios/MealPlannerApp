package com.example.mealplannerfinalproject.data.model.requests

import com.google.gson.annotations.SerializedName

// Data class for meal selection/deselection requests
data class MealsRequestDTO(
    @SerializedName("week")
    val week: String,
    @SerializedName("meal_id")
    val mealID: String,
    @SerializedName("date")
    val date: String
)