package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaservicos.api.ConectaApi
import com.conectaservicos.data.MockData
import com.conectaservicos.model.Service
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import kotlinx.datetime.Clock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ServiceViewModel : ViewModel() {

    val api = ConectaApi()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services.asStateFlow()

    private val _selectedService = MutableStateFlow<Service?>(null)
    val selectedService: StateFlow<Service?> = _selectedService.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadServices()
    }

    fun selectService(id: String) {
        _selectedService.value = MockData.getServiceById(id)
    }

    fun clearSelection() {
        _selectedService.value = null
    }

    fun toggleServiceStatus(id: String) {
        _isLoading.value = true
        val service = _services.value.find { it.id == id }
        if (service != null) {
            val updated = service.copy(isActive = !service.isActive)
            val updatedList = _services.value.map { 
                if (it.id == id) updated else it 
            }
            _services.value = updatedList
        }
        _isLoading.value = false
    }

    fun addService(
        title: String,
        description: String,
        category: String,
        pricePerHour: Double,
        estimatedHours: Int
    ) {
        _isLoading.value = true
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val newService = Service(
            id = "service-$timestamp",
            title = title,
            description = description,
            category = category,
            pricePerHour = pricePerHour,
            estimatedHours = estimatedHours,
            proposalsCount = 0,
            isActive = true,
            createdAt = timestamp.toString()
        )
        _services.value = _services.value + newService
        _isLoading.value = false
    }

    fun deleteService(id: String) {
        _isLoading.value = true
        _services.value = _services.value.filter { it.id != id }
        _isLoading.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun loadServices() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val remoteServices = api.fetchServices()
                _services.value = remoteServices
            } catch (e: Exception) {
                _errorMessage.value = when (e) {
                    is ClientRequestException -> "Erro no servidor. Tente novamente."
                    is ConnectTimeoutException -> "Falha na conexão. Verifique sua internet."
                    else -> "Ocorreu um erro inesperado: ${e.message}"
                }
                println("Erro ao carregar serviços: ${e.message}")
                
                // Fallback: Se falhar a API e não tivermos dados, usamos mocks
                if (_services.value.isEmpty()) {
                    _services.value = MockData.mockServices
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
