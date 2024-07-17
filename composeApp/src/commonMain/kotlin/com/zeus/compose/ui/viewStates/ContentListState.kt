package com.zeus.compose.ui.viewStates

import com.zeus.compose.domain.models.StreamingContent

data class ContentListState(
    val contentList: List<StreamingContent> = emptyList(),
)
