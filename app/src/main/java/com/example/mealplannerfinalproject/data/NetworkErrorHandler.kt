package com.example.mealplannerfinalproject.data

object NetworkErrorHandler {
    fun getErrorMessage(code: Int?, defaultMessage: String? = null): String {
        return when (code) {
            400 -> "Invalid request. Please check your input."
            401 -> "Wrong Email or Password, Please check your credentials."
            403 -> "Access denied. You do not have permission."
            404 -> "Server not found. Please try again later."
            408 -> "Request timeout. Please check your internet connection."
            500 -> "Internal server error. Try again later."
            503 -> "Server unavailable. Please try again later."
            else -> defaultMessage ?: "An unknown error occurred."
        }
    }
}