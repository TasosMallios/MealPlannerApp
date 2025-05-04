package com.example.mealplannerfinalproject.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Extension function to convert yyyy-MM-dd date to d/MM
fun String?.toFormattedDayMonth(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d/MM", Locale.getDefault())
        val date: Date = inputFormat.parse(this) ?: return this.toString()
        outputFormat.format(date)
    } catch (e: Exception) {
        this.toString()
    }
}