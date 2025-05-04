package com.example.mealplannerfinalproject.data.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.mapper.toDomain
import com.example.mealplannerfinalproject.data.model.requests.RegisterRequestDTO
import com.example.mealplannerfinalproject.data.service.AuthServiceAPI
import com.example.mealplannerfinalproject.domain.model.Register
import com.example.mealplannerfinalproject.domain.repository.RegisterRepository
import retrofit2.HttpException
import java.io.IOException

class RegisterRepositoryImpl(val authServiceAPI: AuthServiceAPI) : RegisterRepository {
    override suspend fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): NetworkResult<Register> {
        return try {
            val registerRequest = RegisterRequestDTO(
                firstName = firstName, lastName = lastName, email = email, password = password
            )
            val response = authServiceAPI.registerUser(registerRequest)

            if (response.isSuccessful && response.body() != null) {
                val register = response.body()!!.toDomain()
                if (register != null) {
                    NetworkResult.Success(register)
                }
                else {
                    NetworkResult.Error(message = "Error Signing up")
                }
            } else {
                NetworkResult.Error(message = response.message(), code = response.code())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(
                message = e.message(),
                code = e.code()
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                message = "Network error. Please check your internet connection."
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = "Unexpected error occurred."
            )
        }
    }
}