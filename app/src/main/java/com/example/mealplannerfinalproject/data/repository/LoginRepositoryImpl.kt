package com.example.mealplannerfinalproject.data.repository

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.mapper.toDomain
import com.example.mealplannerfinalproject.data.model.requests.LoginRequestDTO
import com.example.mealplannerfinalproject.data.service.AuthServiceAPI
import com.example.mealplannerfinalproject.domain.model.Login
import com.example.mealplannerfinalproject.domain.repository.LoginRepository
import retrofit2.HttpException
import java.io.IOException

// Login repository implementation
class LoginRepositoryImpl(val authServiceAPI: AuthServiceAPI) : LoginRepository {

    override suspend fun loginUser(email: String, password: String): NetworkResult<Login> {
        return try {
            val loginRequest = LoginRequestDTO(email, password)
            val response = authServiceAPI.loginUser(loginRequest)

            if (response.isSuccessful && response.body() != null) {
                val login = response.body()!!.toDomain()
                if (login != null) {
                    NetworkResult.Success(login)
                }
                else {
                    NetworkResult.Error(message = "Token is missing in the response")
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