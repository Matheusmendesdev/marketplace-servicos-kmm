package com.conectaservicos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.conectaservicos.model.WorkStatus
import com.conectaservicos.ui.components.FilterChip
import com.conectaservicos.ui.components.StatusBadge
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    val workHistory by viewModel.workHistory.collectAsState()
    val filterStatus by viewModel.filterStatus.collectAsState()

    val filteredHistory = if (filterStatus == null) {
        workHistory
    } else {
        workHistory.filter { it.status == filterStatus }
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
                text = "DESEMPENHO",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ConectaColors.Primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Histórico de Trabalhos",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
            Text(
                text = "${filteredHistory.size} trabalho${if (filteredHistory.size != 1) "s" else ""}",
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
                text = "Todos",
                isSelected = filterStatus == null,
                onClick = { viewModel.setFilterStatus(null) }
            )
            FilterChip(
                text = "Concluídos",
                isSelected = filterStatus == WorkStatus.COMPLETED,
                onClick = { viewModel.setFilterStatus(WorkStatus.COMPLETED) }
            )
            FilterChip(
                text = "Em Andamento",
                isSelected = filterStatus == WorkStatus.IN_PROGRESS,
                onClick = { viewModel.setFilterStatus(WorkStatus.IN_PROGRESS) }
            )
            FilterChip(
                text = "Cancelados",
                isSelected = filterStatus == WorkStatus.CANCELLED,
                onClick = { viewModel.setFilterStatus(WorkStatus.CANCELLED) }
            )
        }

        // Work History List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(filteredHistory) { work ->
                val (statusColor, statusTextColor) = when (work.status) {
                    WorkStatus.COMPLETED -> ConectaColors.StatusAccepted to ConectaColors.StatusAcceptedText
                    WorkStatus.IN_PROGRESS -> ConectaColors.StatusNegotiating to ConectaColors.StatusNegotiatingText
                    WorkStatus.CANCELLED -> ConectaColors.StatusRejected to ConectaColors.StatusRejectedText
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, ConectaColors.Border, RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = work.serviceName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ConectaColors.Primary
                            )
                            StatusBadge(
                                status = when (work.status) {
                                    WorkStatus.COMPLETED -> "Concluído"
                                    WorkStatus.IN_PROGRESS -> "Em Andamento"
                                    WorkStatus.CANCELLED -> "Cancelado"
                                },
                                backgroundColor = statusColor,
                                textColor = statusTextColor
                            )
                        }

                        Text(
                            text = work.client.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = ConectaColors.Foreground
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "★".repeat(work.rating.toInt()) + "☆".repeat(5 - work.rating.toInt()),
                                    fontSize = 14.sp,
                                    color = ConectaColors.Warning
                                )
                                Text(
                                    text = String.format("%.1f", work.rating),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ConectaColors.ForegroundSecondary
                                )
                            }
                            Text(
                                text = "R$ ${work.value}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = ConectaColors.Success
                            )
                        }

                        Text(
                            text = "\"${work.comment}\"",
                            fontSize = 12.sp,
                            color = ConectaColors.ForegroundSecondary
                        )
                    }
                }
            }

            if (filteredHistory.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nenhum trabalho neste filtro",
                            fontSize = 16.sp,
                            color = ConectaColors.ForegroundSecondary
                        )
                    }
                }
            }
        }
    }
}
