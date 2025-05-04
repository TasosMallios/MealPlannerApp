package com.example.mealplannerfinalproject.ui.register

// States of UI
data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

// UI event
sealed class RegisterUIEvent {
    data object Success : RegisterUIEvent()
    data object OnLoginClicked : RegisterUIEvent()
    data class ShowToast(val message: String) : RegisterUIEvent()
}

// User actions
sealed class RegisterEvent{
    data class NameChanged(val name: String) : RegisterEvent()
    data class SurnameChanged(val surname: String) : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data object RegisterClicked : RegisterEvent()
    data object LoginClicked : RegisterEvent()
}