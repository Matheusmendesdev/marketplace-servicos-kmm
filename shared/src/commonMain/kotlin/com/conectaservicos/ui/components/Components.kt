package com.conectaservicos.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaservicos.ui.theme.ConectaColors

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ConectaColors.Primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ConectaColors.Surface,
            contentColor = ConectaColors.Primary
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, ConectaColors.Border)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun DangerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ConectaColors.StatusRejected,
            contentColor = ConectaColors.Error
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun StatusBadge(
    status: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        color = backgroundColor
    ) {
        Text(
            text = status,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Composable
fun MetaPill(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(ConectaColors.Surface, RoundedCornerShape(999.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        color = ConectaColors.Surface
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = ConectaColors.ForegroundSecondary
        )
    }
}

@Composable
fun ServiceCard(
    title: String,
    category: String,
    pricePerHour: Double,
    proposalsCount: Int,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
                    text = category,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ConectaColors.Primary
                )
                StatusBadge(
                    status = if (isActive) "Ativo" else "Inativo",
                    backgroundColor = if (isActive) ConectaColors.StatusAccepted else ConectaColors.StatusRejected,
                    textColor = if (isActive) ConectaColors.StatusAcceptedText else ConectaColors.StatusRejectedText
                )
            }
            
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetaPill("R$ $pricePerHour/h")
                MetaPill("$proposalsCount propostas")
            }
        }
    }
}

@Composable
fun ProposalCard(
    clientName: String,
    serviceName: String,
    value: Double,
    status: String,
    statusColor: Color,
    statusTextColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
                    text = serviceName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ConectaColors.Primary
                )
                StatusBadge(
                    status = status,
                    backgroundColor = statusColor,
                    textColor = statusTextColor
                )
            }
            
            Text(
                text = clientName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Foreground
            )
            
            Text(
                text = "R$ $value",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ConectaColors.Success
            )
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick)
            .border(
                1.dp,
                if (isSelected) ConectaColors.Primary else ConectaColors.Border,
                RoundedCornerShape(999.dp)
            )
            .background(
                if (isSelected) ConectaColors.Primary else ConectaColors.Surface,
                RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        color = if (isSelected) ConectaColors.Primary else ConectaColors.Surface
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.White else ConectaColors.ForegroundSecondary
        )
    }
}
