package com.zeus.compose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
actual fun NavigationComponent(
    modifier: Modifier,
    navItems: List<Pair<String, ImageVector>>,
    navigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    when {
                        item.first == "Home" -> {
                            NavigationBarItem(
                                selected = index == selectedItem,
                                onClick = {
                                    selectedItem = index
                                    navigateToHome.invoke()
                                },
                                icon = { Icon(item.second, item.first) }
                            )
                        }
                        else -> {
                            NavigationBarItem(
                                selected = index == selectedItem,
                                onClick = {
                                    selectedItem = index
                                    navigateToHome.invoke()
                                },
                                icon = { Icon(item.second, item.first) }
                            )
                        }
                    }
                }
            }
        }
    ) {
        content.invoke(it)
    }
}