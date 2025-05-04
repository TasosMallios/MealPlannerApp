package com.example.mealplannerfinalproject.data.model

import com.google.gson.annotations.SerializedName

// Data class for week info
data class MealWeekDTO(
    @SerializedName("week") val week: String? = null,
    @SerializedName("days") val days: List<MealDayDTO>? = null,
    @SerializedName("has_next") val hasNext: Boolean? = false,
    @SerializedName("has_previous") val hasPrevious: Boolean? = false,
)
