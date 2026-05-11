package com.conectaservicos.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.conectaservicos.ui.screens.DetailScreen
import com.conectaservicos.ui.screens.HistoryScreen
import com.conectaservicos.ui.screens.LoginScreen
import com.conectaservicos.ui.screens.ProfileScreen
import com.conectaservicos.ui.screens.ProposalsScreen
import com.conectaservicos.ui.screens.ServicesScreen
import com.conectaservicos.ui.theme.ConectaColors
import com.conectaservicos.ui.theme.ConectaTheme
import com.conectaservicos.viewmodel.*

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val serviceViewModel: ServiceViewModel by viewModels()
    private val proposalViewModel: ProposalViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConectaTheme {
                val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

                if (!isLoggedIn) {
                    LoginScreen(
                        viewModel = authViewModel,
                        onLoginSuccess = {
                            // Login handled by ViewModel state
                        }
                    )
                } else {
                    MainNavigation(
                        serviceViewModel = serviceViewModel,
                        proposalViewModel = proposalViewModel,
                        historyViewModel = historyViewModel,
                        profileViewModel = profileViewModel,
                        onLogout = { authViewModel.logout() }
                    )
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Services : Screen("services", "Serviços", Icons.Default.Home)
    object Proposals : Screen("proposals", "Propostas", Icons.AutoMirrored.Filled.List)
    object History : Screen("history", "Histórico", Icons.Default.Refresh)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)

    data class Details(val info: String) : Screen("details", "Detalhes", Icons.Default.Home)
}

@Composable
fun MainNavigation(
    serviceViewModel: ServiceViewModel,
    proposalViewModel: ProposalViewModel,
    historyViewModel: HistoryViewModel,
    profileViewModel: ProfileViewModel,
    onLogout: () -> Unit
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Services) }
    val items = listOf(Screen.Services, Screen.Proposals, Screen.History, Screen.Profile)

    // Debug: Monitora a mudança de tela
    LaunchedEffect(currentScreen) {
        println("Navegação: Mudou para -> ${currentScreen.title}")
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = ConectaColors.Background,
                contentColor = ConectaColors.Primary
            ) {
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ConectaColors.Primary,
                            selectedTextColor = ConectaColors.Primary,
                            unselectedIconColor = ConectaColors.ForegroundTertiary,
                            unselectedTextColor = ConectaColors.ForegroundTertiary,
                            indicatorColor = ConectaColors.SurfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            // Usamos 'val screen = currentScreen' para o Smart Cast funcionar nos detalhes
            when (val screen = currentScreen) {
                is Screen.Services -> ServicesScreen(
                    viewModel = serviceViewModel,
                    onAddService = { /* TODO */ },
                    onServiceSelected = { id ->
                        // Agora isso vai funcionar e disparar a troca de tela
                        currentScreen = Screen.Details("Id do serviço: $id")
                    }
                )

                is Screen.Proposals -> ProposalsScreen(
                    viewModel = proposalViewModel,
                    onProposalSelected = { /* TODO */ }
                )

                is Screen.History -> HistoryScreen(
                    viewModel = historyViewModel
                )

                is Screen.Profile -> ProfileScreen(
                    viewModel = profileViewModel,
                    onLogout = onLogout
                )

                is Screen.Details -> DetailScreen(
                    params = screen.info,
                    onBack = { currentScreen = Screen.Services }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigationPreview() {
    ConectaTheme {
        MainNavigation(
            serviceViewModel = ServiceViewModel(),
            proposalViewModel = ProposalViewModel(),
            historyViewModel = HistoryViewModel(),
            profileViewModel = ProfileViewModel(),
            onLogout = {}
        )
    }
}

