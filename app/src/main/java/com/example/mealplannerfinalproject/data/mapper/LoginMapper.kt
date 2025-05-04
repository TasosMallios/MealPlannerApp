package com.example.mealplannerfinalproject.data.mapper

import com.example.mealplannerfinalproject.data.model.LoginDTO
import com.example.mealplannerfinalproject.domain.model.Login

fun LoginDTO.toDomain(): Login? {
    return results?.let { Login(it) }
}