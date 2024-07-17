package com.zeus.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.zeus.compose.ui.components.NavigationComponent
import com.zeus.compose.ui.screens.LoginScreen
import com.zeus.compose.ui.theme.darkScheme
import kotlinx.coroutines.flow.first
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = darkScheme
    ) {
        val loginViewModel = viewModel {
            Module.getLoginViewModel()
        }
        val validateSessionUseCase = Module.validateSessionUseCase
        val hasSession = remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            hasSession.value = validateSessionUseCase.invoke().first()
        }

        if (hasSession.value.not()) {
            LoginScreen(
                viewModel = loginViewModel,
                navigateToHome = {
                    hasSession.value = true
                }
            )
        } else {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    val navItems = listOf(
        "Home" to Icons.Filled.Home
    )

    Row {
        NavigationComponent(
            navItems = navItems,
            navigateToHome = { navController.navigate(NavScreens.Home.route) }
        ) {
            MainNavGraph(
                navController = navController
            )
        }
    }
}