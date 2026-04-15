package com.conectaservicos.data

import com.conectaservicos.model.*

object MockData {
    val mockServices = listOf(
        Service(
            id = "service-1",
            title = "Limpeza Residencial",
            description = "Limpeza completa de residências com produtos ecológicos",
            category = "Limpeza",
            pricePerHour = 60.0,
            estimatedHours = 3,
            proposalsCount = 12,
            isActive = true,
            createdAt = "2024-01-15"
        ),
        Service(
            id = "service-2",
            title = "Reparo de Encanamento",
            description = "Conserto de vazamentos e instalação de tubulações",
            category = "Reparo",
            pricePerHour = 85.0,
            estimatedHours = 2,
            proposalsCount = 8,
            isActive = true,
            createdAt = "2024-02-10"
        ),
        Service(
            id = "service-3",
            title = "Consultoria Empresarial",
            description = "Consultoria em gestão e estratégia empresarial",
            category = "Consultoria",
            pricePerHour = 150.0,
            estimatedHours = 1,
            proposalsCount = 5,
            isActive = true,
            createdAt = "2024-03-05"
        )
    )

    val mockProposals = listOf(
        Proposal(
            id = "proposal-1",
            serviceName = "Limpeza Residencial",
            description = "Preciso de limpeza profunda em meu apartamento de 3 quartos",
            client = Client(
                id = "client-1",
                name = "Maria Santos",
                rating = 4.8,
                totalJobs = 15
            ),
            offeredValue = 180.0,
            suggestedValue = 200.0,
            proposedDate = "2024-04-20",
            proposedTime = "09:00",
            location = "Av. Paulista, São Paulo - SP",
            status = ProposalStatus.PENDING,
            createdAt = "2024-04-13"
        ),
        Proposal(
            id = "proposal-2",
            serviceName = "Reparo de Encanamento",
            description = "Vazamento na cozinha, precisa de reparo urgente",
            client = Client(
                id = "client-2",
                name = "João Oliveira",
                rating = 4.5,
                totalJobs = 8
            ),
            offeredValue = 150.0,
            suggestedValue = 170.0,
            proposedDate = "2024-04-18",
            proposedTime = "14:00",
            location = "Rua das Flores, Rio de Janeiro - RJ",
            status = ProposalStatus.NEGOTIATING,
            createdAt = "2024-04-12"
        ),
        Proposal(
            id = "proposal-3",
            serviceName = "Consultoria Empresarial",
            description = "Preciso de consultoria para reestruturação da empresa",
            client = Client(
                id = "client-3",
                name = "Ana Costa",
                rating = 5.0,
                totalJobs = 25
            ),
            offeredValue = 400.0,
            suggestedValue = 450.0,
            proposedDate = "2024-04-25",
            proposedTime = "10:00",
            location = null,
            status = ProposalStatus.ACCEPTED,
            createdAt = "2024-04-11"
        )
    )

    val mockWorkHistory = listOf(
        WorkHistory(
            id = "work-1",
            serviceName = "Limpeza Residencial",
            client = Client(
                id = "client-4",
                name = "Pedro Gomes",
                rating = 4.9,
                totalJobs = 20
            ),
            value = 200.0,
            completedDate = "2024-04-10",
            status = WorkStatus.COMPLETED,
            rating = 5.0,
            comment = "Excelente trabalho, muito profissional e atencioso!"
        ),
        WorkHistory(
            id = "work-2",
            serviceName = "Reparo de Encanamento",
            client = Client(
                id = "client-5",
                name = "Carla Silva",
                rating = 4.7,
                totalJobs = 12
            ),
            value = 170.0,
            completedDate = "2024-04-08",
            status = WorkStatus.COMPLETED,
            rating = 4.5,
            comment = "Bom trabalho, resolveu o problema rapidamente"
        ),
        WorkHistory(
            id = "work-3",
            serviceName = "Limpeza Residencial",
            client = Client(
                id = "client-6",
                name = "Lucas Martins",
                rating = 4.6,
                totalJobs = 18
            ),
            value = 180.0,
            completedDate = "2024-04-05",
            status = WorkStatus.COMPLETED,
            rating = 4.8,
            comment = "Muito satisfeito com o resultado final"
        )
    )

    val mockProfessionalProfile = ProfessionalProfile(
        id = "prof-1",
        name = "João Silva",
        specialty = "Serviços Gerais",
        bio = "Profissional experiente com mais de 10 anos no mercado. Oferecemos limpeza, reparo e consultoria com excelência.",
        rating = 4.7,
        totalJobs = 45,
        acceptanceRate = 0.85,
        email = "joao.silva@conectaservicos.com",
        phone = "(11) 98765-4321",
        joinedDate = "2020-06-15"
    )

    fun getServiceById(id: String): Service? = mockServices.find { it.id == id }

    fun getProposalById(id: String): Proposal? = mockProposals.find { it.id == id }

    fun getProposalsByStatus(status: ProposalStatus): List<Proposal> = 
        mockProposals.filter { it.status == status }

    fun getWorkHistoryByStatus(status: WorkStatus): List<WorkHistory> = 
        mockWorkHistory.filter { it.status == status }

    fun updateProposalStatus(id: String, newStatus: ProposalStatus): Proposal? {
        val proposal = getProposalById(id)
        return proposal?.copy(status = newStatus)
    }
}
