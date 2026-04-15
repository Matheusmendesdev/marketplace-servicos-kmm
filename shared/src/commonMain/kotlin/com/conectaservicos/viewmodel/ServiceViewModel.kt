package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import com.conectaservicos.data.MockData
import com.conectaservicos.model.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ServiceViewModel : ViewModel() {
    private val _services = MutableStateFlow(MockData.mockServices)
    val services: StateFlow<List<Service>> = _services.asStateFlow()

    private val _selectedService = MutableStateFlow<Service?>(null)
    val selectedService: StateFlow<Service?> = _selectedService.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

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
        val newService = Service(
            id = "service-${System.currentTimeMillis()}",
            title = title,
            description = description,
            category = category,
            pricePerHour = pricePerHour,
            estimatedHours = estimatedHours,
            proposalsCount = 0,
            isActive = true,
            createdAt = System.currentTimeMillis().toString()
        )
        _services.value = _services.value + newService
        _isLoading.value = false
    }

    fun deleteService(id: String) {
        _isLoading.value = true
        _services.value = _services.value.filter { it.id != id }
        _isLoading.value = false
    }
}
