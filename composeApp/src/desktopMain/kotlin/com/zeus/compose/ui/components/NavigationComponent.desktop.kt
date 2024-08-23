package com.zeus.compose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
actual fun NavigationComponent(
    modifier: Modifier,
    navItems: List<Pair<String, ImageVector>>,
    navigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    LateralNavigationComponent(
        modifier = modifier,
        navItems = navItems,
        navigateToHome = navigateToHome,
        content = content
    )
}