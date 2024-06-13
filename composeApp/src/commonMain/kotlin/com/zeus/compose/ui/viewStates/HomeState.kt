package com.zeus.compose.ui.viewStates

import com.zeus.compose.domain.models.StreamingContent

data class HomeState(
    val isInitialized :Boolean = false,
    val content: List<StreamingContent> = emptyList()
)