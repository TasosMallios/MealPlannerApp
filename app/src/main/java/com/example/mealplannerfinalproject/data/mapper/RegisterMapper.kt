package com.example.mealplannerfinalproject.data.mapper

import com.example.mealplannerfinalproject.data.model.RegisterDTO
import com.example.mealplannerfinalproject.domain.model.Register

fun RegisterDTO.toDomain(): Register? {
    return success?.let { Register(it) }
}