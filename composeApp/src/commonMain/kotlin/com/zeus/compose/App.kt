package com.zeus.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.zeus.compose.ui.screens.LoginScreen
import com.zeus.compose.ui.theme.darkScheme
import com.zeus.compose.utils.hasSession
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
        val hasSession = remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            hasSession.value = hasSession()
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
    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf(
        "Home" to Icons.Filled.Home,
        //"Login" to Icons.Filled.Person,
    )

    Row {
        NavigationRail {
            navItems.forEachIndexed { index, item ->
                when {
                    item.first == "Home" -> {
                        NavigationRailItem(
                            selected = index == selectedItem,
                            onClick = {
                                selectedItem = index
                                navController.navigate(NavScreens.Home.route)
                            },
                            icon = { Icon(item.second, item.first) }
                        )
                    }
                }
            }
        }
        MainNavGraph(
            navController = navController
        )
    }
}