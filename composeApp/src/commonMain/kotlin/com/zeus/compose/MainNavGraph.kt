package com.zeus.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zeus.compose.ui.screens.ContentListScreen
import com.zeus.compose.ui.screens.HomeScreen
import com.zeus.compose.ui.screens.PlayerScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    val homeViewModel = viewModel {
        Module.getHomeViewModel()
    }
    val contentListViewModel = viewModel {
        Module.getContentListViewModel()
    }

    NavHost(
        navController = navController,
        startDestination = NavScreens.Home.route
    ) {
        composable(
            route = NavScreens.Home.route ) {
            HomeScreen(
                viewModel = homeViewModel,
                onContentClick = { isSingleContent, contentName, videoName ->
                    if (isSingleContent) {
                        navController.navigate(NavScreens.Player.setParams(contentName, "", videoName))
                    } else {
                        navController.navigate(NavScreens.ContentList.setParams(contentName))
                    }
                }
            )
        }
        composable(
            route = NavScreens.Player.route,
            arguments = listOf(
                navArgument("contentName") {
                    type = NavType.StringType
                },
                navArgument("chapter") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                },
                navArgument("videoName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val contentName = backStackEntry.arguments?.getString("contentName")
            val chapter = backStackEntry.arguments?.getString("chapter")
            val videoName = backStackEntry.arguments?.getString("videoName")
            println("Video entry $contentName $chapter $videoName")
            PlayerScreen(
                contentName = contentName ?: "",
                chapter = chapter ?: "",
                videoName = videoName ?: ""
            )
        }
        composable(
            route = NavScreens.ContentList.route,
            arguments = listOf(
                navArgument("contentName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val contentName = backStackEntry.arguments?.getString("contentName")?: ""
            ContentListScreen(
                viewModel = contentListViewModel,
                contentName = contentName?: "",
                onContentClick = { isSingleContent, chapterName, videoName ->
                    if (isSingleContent) {
                        navController.navigate(NavScreens.Player.setParams(contentName, chapterName, videoName))
                    } else {
                        navController.navigate(NavScreens.ContentList.setParams(contentName))
                    }
                }
            )
        }
    }
}

sealed class NavScreens(val route: String) {
    data object Home : NavScreens("home")
    data object Player : NavScreens("player?contentName={contentName}&chapter={chapter}&videoName={videoName}") {
        fun setParams(contentName: String, chapter: String, videoName: String): String {
            return "player?contentName=$contentName&chapter=$chapter&videoName=$videoName"
        }
    }
    data object ContentList : NavScreens("contentList?contentName={contentName}") {
        fun setParams(contentName: String): String {
            return "contentList?contentName=$contentName"
        }
    }
}