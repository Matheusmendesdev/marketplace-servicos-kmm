package com.conectaservicos.viewmodel

import androidx.lifecycle.ViewModel
import com.conectaservicos.data.MockData
import com.conectaservicos.model.Proposal
import com.conectaservicos.model.ProposalStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProposalViewModel : ViewModel() {
    private val _proposals = MutableStateFlow(MockData.mockProposals)
    val proposals: StateFlow<List<Proposal>> = _proposals.asStateFlow()

    private val _selectedProposal = MutableStateFlow<Proposal?>(null)
    val selectedProposal: StateFlow<Proposal?> = _selectedProposal.asStateFlow()

    private val _filterStatus = MutableStateFlow<ProposalStatus?>(null)
    val filterStatus: StateFlow<ProposalStatus?> = _filterStatus.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val filteredProposals: StateFlow<List<Proposal>>
        get() {
            val status = _filterStatus.value
            return if (status == null) {
                _proposals
            } else {
                MutableStateFlow(_proposals.value.filter { it.status == status }).asStateFlow()
            }
        }

    fun selectProposal(id: String) {
        _selectedProposal.value = MockData.getProposalById(id)
    }

    fun clearSelection() {
        _selectedProposal.value = null
    }

    fun setFilterStatus(status: ProposalStatus?) {
        _filterStatus.value = status
    }

    fun acceptProposal(id: String) {
        _isLoading.value = true
        val updated = MockData.updateProposalStatus(id, ProposalStatus.ACCEPTED)
        if (updated != null) {
            val updatedList = _proposals.value.map { 
                if (it.id == id) updated else it 
            }
            _proposals.value = updatedList
            _selectedProposal.value = updated
        }
        _isLoading.value = false
    }

    fun rejectProposal(id: String) {
        _isLoading.value = true
        val updated = MockData.updateProposalStatus(id, ProposalStatus.REJECTED)
        if (updated != null) {
            val updatedList = _proposals.value.map { 
                if (it.id == id) updated else it 
            }
            _proposals.value = updatedList
            _selectedProposal.value = updated
        }
        _isLoading.value = false
    }
}
