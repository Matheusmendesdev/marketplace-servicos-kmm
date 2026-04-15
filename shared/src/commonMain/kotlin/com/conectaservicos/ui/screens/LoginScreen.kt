package com.conectaservicos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaservicos.ui.components.PrimaryButton
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("profissional@servico.com") }
    var password by remember { mutableStateOf("senha123") }

    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ConectaColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Surface(
            modifier = Modifier
                .size(80.dp)
                .background(ConectaColors.Primary, RoundedCornerShape(20.dp)),
            color = ConectaColors.Primary
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CS",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Acesse sua operação",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = ConectaColors.Foreground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Entre para acompanhar jobs ativos, comparar propostas e priorizar oportunidades com mais aderência.",
            fontSize = 14.sp,
            color = ConectaColors.ForegroundSecondary,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Form Card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, ConectaColors.Border, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Email Field
                Text(
                    text = "E-mail",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ConectaColors.Foreground
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = ConectaColors.Surface,
                        unfocusedContainerColor = ConectaColors.Surface,
                        focusedIndicatorColor = ConectaColors.Primary,
                        unfocusedIndicatorColor = ConectaColors.Border
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                // Password Field
                Text(
                    text = "Senha",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ConectaColors.Foreground
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = ConectaColors.Surface,
                        unfocusedContainerColor = ConectaColors.Surface,
                        focusedIndicatorColor = ConectaColors.Primary,
                        unfocusedIndicatorColor = ConectaColors.Border
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
                )

                // Error Message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        fontSize = 12.sp,
                        color = ConectaColors.Error,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Login Button
                PrimaryButton(
                    text = "Entrar",
                    onClick = { viewModel.login(email, password) },
                    isLoading = isLoading
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Demo Mode Info
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(ConectaColors.SurfaceVariant, RoundedCornerShape(12.dp)),
            color = ConectaColors.SurfaceVariant
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Modo demonstração",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ConectaColors.Primary
                )
                Text(
                    text = "Use os dados já preenchidos para acessar a primeira versão do app e navegar entre jobs e proposals.",
                    fontSize = 12.sp,
                    color = ConectaColors.ForegroundSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Esqueceu sua senha? Recuperação será conectada nas próximas iterações.",
            fontSize = 12.sp,
            color = ConectaColors.ForegroundSecondary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BorderStroke(width: androidx.compose.ui.unit.Dp, color: Color): BorderStroke {
    return BorderStroke(width, color)
}

@Composable
fun Modifier.border(width: androidx.compose.ui.unit.Dp, color: Color, shape: RoundedCornerShape): Modifier {
    return this.border(width, color, shape)
}
