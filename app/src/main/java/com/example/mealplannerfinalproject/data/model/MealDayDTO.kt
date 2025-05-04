package com.example.mealplannerfinalproject.data.model

import com.google.gson.annotations.SerializedName

// Data class for day info
data class MealDayDTO(
    @SerializedName("date") val date: String? = null,
    @SerializedName("meals") val meals: List<MealDTO>? = null
)
