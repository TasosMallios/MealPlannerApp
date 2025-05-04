package com.example.mealplannerfinalproject.ui.login

// States of UI
data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

// UI event
sealed class LoginUIEvent {
    data object Success : LoginUIEvent()
    data object OnRegisterClicked : LoginUIEvent()
    data class ShowToast(val message: String) : LoginUIEvent()
}

// User actions
sealed class LoginEvent{
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object LoginClicked : LoginEvent()
    data object RegisterClicked : LoginEvent()
}