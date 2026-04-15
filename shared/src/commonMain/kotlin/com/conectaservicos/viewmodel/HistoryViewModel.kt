package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import com.conectaservicos.data.MockData
import com.conectaservicos.model.WorkHistory
import com.conectaservicos.model.WorkStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel : ViewModel() {
    private val _workHistory = MutableStateFlow(MockData.mockWorkHistory)
    val workHistory: StateFlow<List<WorkHistory>> = _workHistory.asStateFlow()

    private val _selectedWork = MutableStateFlow<WorkHistory?>(null)
    val selectedWork: StateFlow<WorkHistory?> = _selectedWork.asStateFlow()

    private val _filterStatus = MutableStateFlow<WorkStatus?>(null)
    val filterStatus: StateFlow<WorkStatus?> = _filterStatus.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val filteredWorkHistory: StateFlow<List<WorkHistory>>
        get() {
            val status = _filterStatus.value
            return if (status == null) {
                _workHistory
            } else {
                MutableStateFlow(_workHistory.value.filter { it.status == status }).asStateFlow()
            }
        }

    val averageRating: StateFlow<Double>
        get() {
            val avg = if (_workHistory.value.isNotEmpty()) {
                _workHistory.value.map { it.rating }.average()
            } else {
                0.0
            }
            return MutableStateFlow(avg).asStateFlow()
        }

    val completedJobsCount: StateFlow<Int>
        get() {
            val count = _workHistory.value.count { it.status == WorkStatus.COMPLETED }
            return MutableStateFlow(count).asStateFlow()
        }

    fun selectWork(id: String) {
        _selectedWork.value = _workHistory.value.find { it.id == id }
    }

    fun clearSelection() {
        _selectedWork.value = null
    }

    fun setFilterStatus(status: WorkStatus?) {
        _filterStatus.value = status
    }
}
