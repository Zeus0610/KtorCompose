package com.zeus.compose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.layout.onGloballyPositioned
import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.ui.viewModels.PlayerViewModel
import com.zeus.compose.ui.viewModels.PlayerViewModelImpl

@Composable
actual fun PlayerScreen(
    viewModel: PlayerViewModel,
    contentName: String,
    chapter: String,
    videoName: String
) {
    viewModel as PlayerViewModelImpl
    val state = viewModel.state.collectAsState()

    DisposableEffect(Unit) {
        val videoUrl = if (chapter.isEmpty()) {
            EndPoints.videoUrl(contentName, videoName)
        } else {
            EndPoints.videoUrl(contentName,  chapter, videoName)
        }
        viewModel.initPlayer(videoUrl)
        onDispose {
            viewModel.releasePlayer()
        }
    }

    if(state.value.player != null) {
        SwingPanel(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    viewModel.playPause()
                },
            factory = {
                state.value.player!!
            }
        )
    } else {
        Text("Cargando...")
    }
}