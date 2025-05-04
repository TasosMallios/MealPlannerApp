package com.example.mealplannerfinalproject.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Extension function to convert yyyy-MM-dd date to yyyy-ww
fun String?.toFormattedYearWeek(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val date = LocalDate.parse(this, inputFormatter)

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-ww")

    return date.format(outputFormatter)
}