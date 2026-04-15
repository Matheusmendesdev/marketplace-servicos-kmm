package com.conectaservicos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaservicos.ui.components.DangerButton
import com.conectaservicos.ui.components.PrimaryButton
import com.conectaservicos.ui.components.SecondaryButton
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit
) {
    val profile by viewModel.profile.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val completedJobs = viewModel.getCompletedJobsCount()
    val averageRating = viewModel.getAverageRating()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ConectaColors.Background)
            .verticalScroll(rememberScrollState())
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
                text = "PERFIL",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = ConectaColors.Primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Meu Perfil Profissional",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Profile Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, ConectaColors.Border, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .size(80.dp)
                            .background(ConectaColors.Primary, RoundedCornerShape(40.dp)),
                        color = ConectaColors.Primary
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = profile.name.first().uppercase(),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Text(
                        text = profile.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = ConectaColors.Foreground
                    )

                    Text(
                        text = profile.specialty,
                        fontSize = 14.sp,
                        color = ConectaColors.ForegroundSecondary
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "★★★★★",
                            fontSize = 16.sp,
                            color = ConectaColors.Warning
                        )
                        Text(
                            text = String.format("%.1f", profile.rating),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ConectaColors.Foreground
                        )
                    }

                    Text(
                        text = profile.bio,
                        fontSize = 13.sp,
                        color = ConectaColors.ForegroundSecondary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Statistics
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Estatísticas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ConectaColors.Foreground
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .background(ConectaColors.Surface, RoundedCornerShape(16.dp))
                            .border(1.dp, ConectaColors.Border, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        color = ConectaColors.Surface
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "${profile.totalJobs}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ConectaColors.Primary
                            )
                            Text(
                                text = "Trabalhos",
                                fontSize = 11.sp,
                                color = ConectaColors.ForegroundSecondary
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .background(ConectaColors.Surface, RoundedCornerShape(16.dp))
                            .border(1.dp, ConectaColors.Border, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        color = ConectaColors.Surface
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = String.format("%.1f", averageRating),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ConectaColors.Primary
                            )
                            Text(
                                text = "Avaliação Média",
                                fontSize = 11.sp,
                                color = ConectaColors.ForegroundSecondary
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .background(ConectaColors.Surface, RoundedCornerShape(16.dp))
                            .border(1.dp, ConectaColors.Border, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        color = ConectaColors.Surface
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "${(profile.acceptanceRate * 100).toInt()}%",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = ConectaColors.Primary
                            )
                            Text(
                                text = "Taxa Aceita",
                                fontSize = 11.sp,
                                color = ConectaColors.ForegroundSecondary
                            )
                        }
                    }
                }
            }

            // Contact Information
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Informações de Contato",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ConectaColors.Foreground
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ConectaColors.Surface, RoundedCornerShape(12.dp))
                        .border(1.dp, ConectaColors.Border, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    color = ConectaColors.Surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "E-mail",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ConectaColors.ForegroundSecondary
                        )
                        Text(
                            text = profile.email,
                            fontSize = 14.sp,
                            color = ConectaColors.Foreground,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PrimaryButton(
                    text = "Editar Perfil",
                    onClick = { viewModel.startEditing() },
                    isLoading = isLoading
                )

                SecondaryButton(
                    text = "Alterar Foto",
                    onClick = {}
                )

                DangerButton(
                    text = "Sair da Conta",
                    onClick = onLogout
                )
            }
        }
    }
}
