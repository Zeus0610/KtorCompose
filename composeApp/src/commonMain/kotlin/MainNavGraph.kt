import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.repository.getHomeRepository
import domain.usecases.GetGreetingsUseCase
import ui.screens.HomeScreen
import ui.screens.LoginScreen
import viewModels.HomeViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    val homeViewModel = viewModel {
        val homeRepository = getHomeRepository()
        val greetingsUseCase = GetGreetingsUseCase(homeRepository)
        HomeViewModel(greetingsUseCase)
    }
    NavHost(
        navController = navController,
        startDestination = NavScreens.Home.route
    ) {
        composable(NavScreens.Home.route) {
            HomeScreen()
        }
        composable(NavScreens.Login.route) {
            LoginScreen(homeViewModel)
        }
    }
}

sealed class NavScreens(val route: String) {
    data object Home : NavScreens("home")
    data object Login : NavScreens("login")
}