package com.example.mealplannerfinalproject.data

sealed class NetworkResult<out T> {

    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val message: String?, val code: Int? = null): NetworkResult<T>()

    companion object {
        fun <T> success(data: T): NetworkResult<T> = Success(data)
        fun <T> error(message: String?, code: Int?): NetworkResult<T> = Error(message, code)
    }
}