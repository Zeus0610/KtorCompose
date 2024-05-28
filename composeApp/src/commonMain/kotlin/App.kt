import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        var selectedItem by remember { mutableStateOf(0) }
        val navItems = listOf(
            "Home" to Icons.Filled.Home,
            "Login" to Icons.Filled.Person,
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

                        item.first == "Login" -> {
                            NavigationRailItem(
                                selected = index == selectedItem,
                                onClick = {
                                    selectedItem = index
                                    navController.navigate(NavScreens.Login.route)
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
}