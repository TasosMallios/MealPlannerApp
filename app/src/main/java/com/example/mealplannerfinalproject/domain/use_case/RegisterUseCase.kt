package com.example.mealplannerfinalproject.domain.use_case

import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.model.Register
import com.example.mealplannerfinalproject.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository,
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): NetworkResult<Register> {

        val result = repository.registerUser(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )

        return result

    }
}