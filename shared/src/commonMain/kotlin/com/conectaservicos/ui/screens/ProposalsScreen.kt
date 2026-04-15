package com.conectaservicos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaservicos.model.ProposalStatus
import com.conectaservicos.ui.components.FilterChip
import com.conectaservicos.ui.components.ProposalCard
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.viewmodel.ProposalViewModel

@Composable
fun ProposalsScreen(
    viewModel: ProposalViewModel,
    onProposalSelected: (String) -> Unit
) {
    val proposals by viewModel.proposals.collectAsState()
    val filterStatus by viewModel.filterStatus.collectAsState()

    val filteredProposals = if (filterStatus == null) {
        proposals
    } else {
        proposals.filter { it.status == filterStatus }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ConectaColors.Background)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ConectaColors.Background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "OPORTUNIDADES",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ConectaColors.Primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Propostas Recebidas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
            Text(
                text = "${filteredProposals.size} proposta${if (filteredProposals.size != 1) "s" else ""}",
                fontSize = 14.sp,
                color = ConectaColors.ForegroundSecondary
            )
        }

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = "Todas",
                isSelected = filterStatus == null,
                onClick = { viewModel.setFilterStatus(null) }
            )
            FilterChip(
                text = "Pendentes",
                isSelected = filterStatus == ProposalStatus.PENDING,
                onClick = { viewModel.setFilterStatus(ProposalStatus.PENDING) }
            )
            FilterChip(
                text = "Aceitas",
                isSelected = filterStatus == ProposalStatus.ACCEPTED,
                onClick = { viewModel.setFilterStatus(ProposalStatus.ACCEPTED) }
            )
            FilterChip(
                text = "Recusadas",
                isSelected = filterStatus == ProposalStatus.REJECTED,
                onClick = { viewModel.setFilterStatus(ProposalStatus.REJECTED) }
            )
            FilterChip(
                text = "Negociando",
                isSelected = filterStatus == ProposalStatus.NEGOTIATING,
                onClick = { viewModel.setFilterStatus(ProposalStatus.NEGOTIATING) }
            )
        }

        // Proposals List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(filteredProposals) { proposal ->
                val (statusColor, statusTextColor) = when (proposal.status) {
                    ProposalStatus.PENDING -> ConectaColors.StatusPending to ConectaColors.StatusPendingText
                    ProposalStatus.ACCEPTED -> ConectaColors.StatusAccepted to ConectaColors.StatusAcceptedText
                    ProposalStatus.REJECTED -> ConectaColors.StatusRejected to ConectaColors.StatusRejectedText
                    ProposalStatus.NEGOTIATING -> ConectaColors.StatusNegotiating to ConectaColors.StatusNegotiatingText
                }

                ProposalCard(
                    clientName = proposal.client.name,
                    serviceName = proposal.serviceName,
                    value = proposal.offeredValue,
                    status = when (proposal.status) {
                        ProposalStatus.PENDING -> "Pendente"
                        ProposalStatus.ACCEPTED -> "Aceita"
                        ProposalStatus.REJECTED -> "Recusada"
                        ProposalStatus.NEGOTIATING -> "Negociando"
                    },
                    statusColor = statusColor,
                    statusTextColor = statusTextColor,
                    onClick = { onProposalSelected(proposal.id) }
                )
            }

            if (filteredProposals.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nenhuma proposta neste filtro",
                            fontSize = 16.sp,
                            color = ConectaColors.ForegroundSecondary
                        )
                    }
                }
            }
        }
    }
}
