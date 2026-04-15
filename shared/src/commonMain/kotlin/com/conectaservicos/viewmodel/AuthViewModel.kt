package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUserEmail = MutableStateFlow("")
    val currentUserEmail: StateFlow<String> = _currentUserEmail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    fun login(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = ""

        // Simulação de autenticação local
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _currentUserEmail.value = email
            _isLoggedIn.value = true
            _isLoading.value = false
        } else {
            _errorMessage.value = "Email e senha são obrigatórios"
            _isLoading.value = false
        }
    }

    fun logout() {
        _isLoggedIn.value = false
        _currentUserEmail.value = ""
        _errorMessage.value = ""
    }

    fun clearError() {
        _errorMessage.value = ""
    }
}
