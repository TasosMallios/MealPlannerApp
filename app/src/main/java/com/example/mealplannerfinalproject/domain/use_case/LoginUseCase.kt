package com.example.mealplannerfinalproject.domain.use_case

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.local.SessionManager
import com.example.mealplannerfinalproject.domain.model.Login
import com.example.mealplannerfinalproject.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(email: String, password:String): NetworkResult<Login> {
        val result = repository.loginUser(email, password)
        if(result is NetworkResult.Success){
            sessionManager.saveToken(result.data.token ?: "")
        }
        return result
    }
}