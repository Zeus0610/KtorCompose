package com.zeus.compose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
expect fun NavigationComponent(
    modifier: Modifier = Modifier,
    navItems: List<Pair<String, ImageVector>>,
    navigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
)