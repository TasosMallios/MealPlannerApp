package com.example.mealplannerfinalproject.ui.model

data class MealUI(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val type: String? = null,
    val calories: Int? = null,
    val selected: Boolean? = false,
    val liked: Boolean? = false
)
