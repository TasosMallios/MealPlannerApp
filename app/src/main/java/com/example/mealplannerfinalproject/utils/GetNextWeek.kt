package com.example.mealplannerfinalproject.utils

import java.util.Calendar

// Calculate next week
fun getNextWeek(currentWeek: String): String {
    val parts = currentWeek.split("-")
    val year = parts[0].toInt()
    val week = parts[1].toInt()

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.WEEK_OF_YEAR, week)
    calendar.add(Calendar.WEEK_OF_YEAR, 1)

    val nextYear = calendar.get(Calendar.YEAR)
    val nextWeek = calendar.get(Calendar.WEEK_OF_YEAR)
    return "$nextYear-$nextWeek"
}