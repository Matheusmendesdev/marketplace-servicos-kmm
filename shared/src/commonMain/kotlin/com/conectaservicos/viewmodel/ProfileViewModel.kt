package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import com.conectaservicos.data.MockData
import com.conectaservicos.model.ProfessionalProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _profile = MutableStateFlow(MockData.mockProfessionalProfile)
    val profile: StateFlow<ProfessionalProfile> = _profile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> = _isEditing.asStateFlow()

    fun startEditing() {
        _isEditing.value = true
    }

    fun cancelEditing() {
        _isEditing.value = false
    }

    fun updateProfile(
        name: String,
        specialty: String,
        bio: String,
        phone: String?
    ) {
        _isLoading.value = true
        val updated = _profile.value.copy(
            name = name,
            specialty = specialty,
            bio = bio,
            phone = phone
        )
        _profile.value = updated
        _isEditing.value = false
        _isLoading.value = false
    }

    fun getAverageRating(): Double {
        val workHistory = MockData.mockWorkHistory
        return if (workHistory.isNotEmpty()) {
            workHistory.map { it.rating }.average()
        } else {
            0.0
        }
    }

    fun getCompletedJobsCount(): Int {
        return MockData.mockWorkHistory.count { 
            it.status == com.conectaservicos.model.WorkStatus.COMPLETED 
        }
    }
}
