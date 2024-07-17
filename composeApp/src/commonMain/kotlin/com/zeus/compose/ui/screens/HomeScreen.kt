package com.zeus.compose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.zeus.compose.ui.components.ContentList
import com.zeus.compose.ui.viewEvents.HomeEvents
import com.zeus.compose.ui.viewModels.HomeViewModel
import ktorcompose.composeapp.generated.resources.Res
import ktorcompose.composeapp.generated.resources.home
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onContentClick: (isSingleContent: Boolean, contentName: String, videoName: String) -> Unit = { _, _, _ -> }
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        if (state.isInitialized.not()) {
            viewModel.onEvent(HomeEvents.OnInitScreen)
        }
    }

    ContentList(
        title = stringResource(Res.string.home),
        contentList = state.content,
        onContentClick = onContentClick,
    )
}