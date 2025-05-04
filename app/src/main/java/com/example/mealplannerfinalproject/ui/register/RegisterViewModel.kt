package com.example.mealplannerfinalproject.ui.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplannerfinalproject.data.NetworkErrorHandler
import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _registerUIEvent = MutableSharedFlow<RegisterUIEvent>()
    val registerUIEvent = _registerUIEvent.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.NameChanged -> {
                _state.update { it.copy(firstName = event.name, firstNameError = null) }
            }

            is RegisterEvent.SurnameChanged -> {
                _state.update { it.copy(lastName = event.surname, lastNameError = null) }
            }

            is RegisterEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email, emailError = null) }
            }

            is RegisterEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password, passwordError = null) }
            }

            is RegisterEvent.RegisterClicked -> {
                if (_state.value.firstName.isBlank()) {
                    _state.update { it.copy(firstNameError = "Name cannot be empty") }
                    return
                }
                if (_state.value.lastName.isBlank()) {
                    _state.update { it.copy(lastNameError = "Surname cannot be empty") }
                    return
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches() || _state.value.email.isBlank()) {
                    _state.update { it.copy(emailError = "Please enter a valid email") }
                    return
                }
                if (_state.value.password.length < 6) {
                    _state.update { it.copy(passwordError = "Password cannot be less than 6 characters") }
                    return
                }
                register()
            }

            is RegisterEvent.LoginClicked -> {
                viewModelScope.launch {
                    _registerUIEvent.emit(RegisterUIEvent.OnLoginClicked)
                }
            }
        }
    }

    fun register() = viewModelScope.launch {

        _state.update { it.copy(isLoading = true) }

        val name = _state.value.firstName
        val surname = _state.value.lastName
        val email = _state.value.email
        val password = _state.value.password

        val registerResult = registerUseCase(firstName = name, lastName = surname, email = email, password = password)

        _state.update { it.copy(isLoading = false) }

        when (registerResult) {
            is NetworkResult.Success -> {
                _registerUIEvent.emit(RegisterUIEvent.Success)
            }

            is NetworkResult.Error -> {
                val message = NetworkErrorHandler.getErrorMessage(
                    code = registerResult.code,
                    defaultMessage = registerResult.message
                )
                _registerUIEvent.emit(RegisterUIEvent.ShowToast(message))
            }
        }
    }

}