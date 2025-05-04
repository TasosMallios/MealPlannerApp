package com.example.mealplannerfinalproject.utils

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

// Compare a given week with the current one
fun String?.isLaterThanCurrentWeek(): Boolean? {
    if (this.isNullOrEmpty()) return null

    val current = LocalDate.now()
    val currentYear = current.year
    val currentWeek = current.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())

    val givenParts = this.split("-")
    if (givenParts.size != 2) return null

    val givenYear = givenParts[0].toIntOrNull() ?: return null
    val givenWeekNumber = givenParts[1].toIntOrNull() ?: return null

    return when {
        givenYear < currentYear || (givenYear == currentYear && givenWeekNumber < currentWeek) -> false
        else -> true
    }
}