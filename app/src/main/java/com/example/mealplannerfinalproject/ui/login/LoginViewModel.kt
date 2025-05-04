package com.example.mealplannerfinalproject.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplannerfinalproject.data.NetworkErrorHandler
import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.local.SessionManager
import com.example.mealplannerfinalproject.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _loginUIEvent = MutableSharedFlow<LoginUIEvent>()
    val loginUIEvent = _loginUIEvent.asSharedFlow()

    private val sessionManager = SessionManager(application.applicationContext)

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email, emailError = null) }
            }

            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password, passwordError = null) }
            }

            is LoginEvent.LoginClicked -> {
                if (!Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches() || _state.value.email.isBlank()) {
                    _state.update { it.copy(emailError = "Please enter a valid email") }
                    return
                }
                if (_state.value.password.length < 6) {
                    _state.update { it.copy(passwordError = "Password cannot be less than 6 characters") }
                    return
                }
                login()
            }

            is LoginEvent.RegisterClicked -> {
                viewModelScope.launch {
                    _loginUIEvent.emit(LoginUIEvent.OnRegisterClicked)
                }
            }
        }
    }

    // Login function
    fun login() = viewModelScope.launch {

        _state.update { it.copy(isLoading = true) }

        val email = _state.value.email
        val password = _state.value.password
        val loginResult = loginUseCase(email = email, password = password)

        _state.update { it.copy(isLoading = false) }

        when (loginResult) {
            is NetworkResult.Success -> {
                _loginUIEvent.emit(LoginUIEvent.Success)
            }

            is NetworkResult.Error -> {
                val message = NetworkErrorHandler.getErrorMessage(
                    code = loginResult.code,
                    defaultMessage = loginResult.message
                )
                _loginUIEvent.emit(LoginUIEvent.ShowToast(message))
            }
        }
    }

    // Get JWT token
    fun getSavedToken(): String? {
        return sessionManager.getToken()
    }

    // Clear JWT token from shared preferences
    fun logout() {
        sessionManager.clearToken()
    }

}