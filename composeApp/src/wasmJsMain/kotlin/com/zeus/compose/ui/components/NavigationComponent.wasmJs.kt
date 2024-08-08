package com.zeus.compose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
actual fun NavigationComponent(
    modifier: Modifier,
    navItems: List<Pair<String, ImageVector>>,
    navigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }
    Row {
        NavigationRail {
            navItems.forEachIndexed { index, item ->
                when {
                    item.first == "Home" -> {
                        NavigationRailItem(
                            selected = index == selectedItem,
                            onClick = {
                                selectedItem = index
                                navigateToHome.invoke()
                            },
                            icon = { Icon(item.second, item.first) }
                        )
                    }
                    else -> {
                        NavigationRailItem(
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
        content.invoke(PaddingValues(0.dp))
    }
}