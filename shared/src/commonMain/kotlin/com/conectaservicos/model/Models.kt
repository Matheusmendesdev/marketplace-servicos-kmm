package com.conectaservicos.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val pricePerHour: Double,
    val estimatedHours: Int,
    val proposalsCount: Int,
    val isActive: Boolean,
    val createdAt: String
)

@Serializable
data class Client(
    val id: String,
    val name: String,
    val rating: Double,
    val totalJobs: Int
)

@Serializable
data class Proposal(
    val id: String,
    val serviceName: String,
    val description: String,
    val client: Client,
    val offeredValue: Double,
    val suggestedValue: Double,
    val proposedDate: String,
    val proposedTime: String,
    val location: String?,
    val status: ProposalStatus,
    val createdAt: String
)

enum class ProposalStatus {
    PENDING, ACCEPTED, REJECTED, NEGOTIATING
}

@Serializable
data class WorkHistory(
    val id: String,
    val serviceName: String,
    val client: Client,
    val value: Double,
    val completedDate: String,
    val status: WorkStatus,
    val rating: Double,
    val comment: String
)

enum class WorkStatus {
    COMPLETED, IN_PROGRESS, CANCELLED
}

@Serializable
data class ProfessionalProfile(
    val id: String,
    val name: String,
    val specialty: String,
    val bio: String,
    val rating: Double,
    val totalJobs: Int,
    val acceptanceRate: Double,
    val email: String,
    val phone: String?,
    val joinedDate: String
)

@Serializable
data class User(
    val id: String,
    val email: String,
    val password: String,
    val profile: ProfessionalProfile
)

@Serializable
data class Login(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String? = null,
    val id: String? = null
)