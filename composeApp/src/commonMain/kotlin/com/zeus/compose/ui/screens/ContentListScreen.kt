package com.zeus.compose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.zeus.compose.ui.components.ContentList
import com.zeus.compose.ui.viewEvents.ContentListEvents
import com.zeus.compose.ui.viewModels.ContentListViewModel

@Composable
fun ContentListScreen(
    viewModel: ContentListViewModel,
    contentName: String = "",
    onContentClick: (Boolean, String, String) -> Unit,
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.onEvent(ContentListEvents.OnInitScreen(contentName))
    }

    ContentList(
        title = contentName,
        contentList = state.contentList,
        onContentClick = onContentClick,
    )
}