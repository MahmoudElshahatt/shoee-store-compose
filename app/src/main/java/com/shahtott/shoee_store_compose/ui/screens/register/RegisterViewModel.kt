package com.shahtott.shoee_store_compose.ui.screens.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    fun onFullNameChange(fullName: String) {
        _registerState.value = _registerState.value.copy(fullName = fullName)
    }

    fun onEmailChange(email: String) {
        _registerState.value = _registerState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _registerState.value = _registerState.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _registerState.value = _registerState.value.copy(confirmPassword = confirmPassword)
    }

    fun onRegisterClick() {

    }
}

data class RegisterState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)