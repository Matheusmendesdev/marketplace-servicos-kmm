package com.conectaservicos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaservicos.ui.components.PrimaryButton
import com.conectaservicos.ui.components.ServiceCard
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.viewmodel.ServiceViewModel

@Composable
fun ServicesScreen(
    viewModel: ServiceViewModel,
    onAddService: () -> Unit,
    onServiceSelected: (String) -> Unit
) {
    val services by viewModel.services.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

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
                text = "MEUS SERVIÇOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ConectaColors.Primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Serviços Cadastrados",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
            Text(
                text = "${services.size} serviço${if (services.size != 1) "s" else ""}",
                fontSize = 14.sp,
                color = ConectaColors.ForegroundSecondary
            )
        }

        // Services List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(services) { service ->
                ServiceCard(
                    title = service.title,
                    category = service.category,
                    pricePerHour = service.pricePerHour,
                    proposalsCount = service.proposalsCount,
                    isActive = service.isActive,
                    onClick = { onServiceSelected(service.id) }
                )
            }

            if (services.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nenhum serviço cadastrado",
                            fontSize = 16.sp,
                            color = ConectaColors.ForegroundSecondary
                        )
                    }
                }
            }
        }

        // Add Service Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ConectaColors.Background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PrimaryButton(
                text = "+ Adicionar Novo Serviço",
                onClick = onAddService,
                isLoading = isLoading
            )
        }
    }
}
