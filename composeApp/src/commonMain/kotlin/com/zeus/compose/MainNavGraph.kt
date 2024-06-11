package com.zeus.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeus.compose.ui.screens.HomeScreen
import com.zeus.compose.ui.screens.PlayerScreen

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
            HomeScreen(
                onContentClick = {
                    navController.navigate(NavScreens.Player.route)
                }
            )
        }
        composable(NavScreens.Player.route) {
            PlayerScreen("http://localhost/api/video/Halo Fall of Reach/Halo Fall of Reach.mpd")
        }
    }
}

sealed class NavScreens(val route: String) {
    data object Home : NavScreens("home")
    //data object Login : NavScreens("login")
    data object Player: NavScreens("player")
}