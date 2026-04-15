package com.conectaservicos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cores exclusivas do Conecta Serviços
object ConectaColors {
    val Primary = Color(0xFF0F3D56)
    val PrimaryLight = Color(0xFF1E5A7A)
    val PrimaryDark = Color(0xFF0A2A3F)
    
    val Secondary = Color(0xFF27AE60)
    val SecondaryLight = Color(0xFF52C77E)
    val SecondaryDark = Color(0xFF1E8449)
    
    val Accent = Color(0xFFF39C12)
    val AccentLight = Color(0xFFF5B041)
    val AccentDark = Color(0xFFD68910)
    
    val Background = Color(0xFFFFFFFF)
    val Surface = Color(0xFFF7FAFC)
    val SurfaceVariant = Color(0xFFE8F0FC)
    
    val Foreground = Color(0xFF102A43)
    val ForegroundSecondary = Color(0xFF5C6B7A)
    val ForegroundTertiary = Color(0xFF9BA1A6)
    
    val Border = Color(0xFFD9E2EC)
    val BorderLight = Color(0xFFE5E7EB)
    
    val Success = Color(0xFF27AE60)
    val Warning = Color(0xFFF39C12)
    val Error = Color(0xFFC41E3A)
    
    val StatusPending = Color(0xFFFEF3E2)
    val StatusPendingText = Color(0xFFB8860B)
    
    val StatusAccepted = Color(0xFFE8FCF8)
    val StatusAcceptedText = Color(0xFF128579)
    
    val StatusRejected = Color(0xFFFCE8E8)
    val StatusRejectedText = Color(0xFFC41E3A)
    
    val StatusNegotiating = Color(0xFFE8F0FC)
    val StatusNegotiatingText = Color(0xFF1E40AF)
}

private val LightColorScheme = lightColorScheme(
    primary = ConectaColors.Primary,
    secondary = ConectaColors.Secondary,
    tertiary = ConectaColors.Accent,
    background = ConectaColors.Background,
    surface = ConectaColors.Surface,
    error = ConectaColors.Error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = ConectaColors.Foreground,
    onSurface = ConectaColors.Foreground,
    onError = Color.White
)

@Composable
fun ConectaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
