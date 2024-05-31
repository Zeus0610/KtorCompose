package com.zeus.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeus.compose.data.repository.getHomeRepository
import com.zeus.compose.data.repository.getLoginRepository
import com.zeus.compose.domain.usecases.GetGreetingsUseCase
import com.zeus.compose.domain.usecases.LoginUseCase
import com.zeus.compose.ui.screens.HomeScreen
import com.zeus.compose.ui.screens.LoginScreen
import com.zeus.compose.ui.viewModels.HomeViewModel
import com.zeus.compose.ui.viewModels.LoginViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    val homeViewModel = viewModel {
        Module.getHomeViewModel()
    }

    NavHost(
        navController = navController,
        startDestination = NavScreens.Home.route
    ) {
        composable(NavScreens.Home.route) {
            HomeScreen()
        }
    }
}

sealed class NavScreens(val route: String) {
    data object Home : NavScreens("home")
    data object Login : NavScreens("login")
}