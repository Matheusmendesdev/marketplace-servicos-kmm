package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaservicos.api.ConectaApi
import com.conectaservicos.model.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val api = ConectaApi()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUserEmail = MutableStateFlow("")
    val currentUserEmail: StateFlow<String> = _currentUserEmail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || !email.contains("@")) {
            _errorMessage.value = "Por favor, insira um e-mail válido"
            return
        }

        if (password.isBlank() || password.length < 4) {
            _errorMessage.value = "A senha deve ter pelo menos 4 caracteres"
            return
        }

        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch {
            try {
                val loginRequest = Login(email = email, password = password)
                val response = api.fetchLogin(loginRequest)

                if (!response.token.isNullOrEmpty()) {
                    _currentUserEmail.value = email
                    _isLoggedIn.value = true
                } else {
                    _errorMessage.value = "E-mail ou senha inválidos"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro de conexão: ${e.message}"
                println("Erro ao realizar login: ${e.message}")
            } finally {
                _isLoading.value = false
            }
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
