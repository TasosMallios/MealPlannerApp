package com.example.mealplannerfinalproject.data.model

import com.google.gson.annotations.SerializedName

// Data class for meal info
data class MealDTO(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("calories") val calories: Int? = null,
    @SerializedName("selected") val selected: Boolean? = false,
    @SerializedName("liked") val liked: Boolean? = false
)
