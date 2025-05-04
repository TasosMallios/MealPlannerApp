package com.example.mealplannerfinalproject.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Extension function to convert yyyy-MM-dd to greek day d/M/yyyy
fun String?.toFormattedDayDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE d/M/yyyy", Locale.getDefault())
        val date: Date = inputFormat.parse(this) ?: return this.toString()
        val formattedDate = outputFormat.format(date)

        return formattedDate

    } catch (e: Exception) {
        this.toString()
    }
}