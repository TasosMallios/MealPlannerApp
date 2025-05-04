package com.example.mealplannerfinalproject.domain.model

data class Meal(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val calories: Int,
    val selected: Boolean,
    val liked: Boolean
)
