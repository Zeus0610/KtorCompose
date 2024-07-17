package com.zeus.compose.ui.screens

import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.ui.PlayerView
import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.ui.viewModels.PlayerViewModel
import com.zeus.compose.ui.viewModels.PlayerViewModelImpl

@Composable
actual fun PlayerScreen(viewModel: PlayerViewModel, contentName: String, chapter: String, videoName: String) {
    viewModel as PlayerViewModelImpl
    val state = viewModel.state.collectAsStateWithLifecycle()
    KeepScreenOn()
    val context = LocalContext.current

    PlayerLifeCycle(
        onInit = {
            val videoUrl = if (chapter.isEmpty()) {
                EndPoints.videoUrl(contentName, videoName)
            } else {
                EndPoints.videoUrl(contentName,  chapter, videoName)
            }
            viewModel.initPlayer(videoUrl, context)
        },
        onRelease = viewModel::releasePlayer
    )

    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .focusable(),
        factory = { PlayerView(it) },
        update = { playerView ->
            playerView.player = state.value.player
        }
    )
}

@Composable
private fun PlayerLifeCycle(
    onInit: () -> Unit,
    onRelease: () -> Unit
) {
    val currentOnInitializePlayer by rememberUpdatedState(newValue = onInit)
    val currentOnReleasePlayer by rememberUpdatedState(newValue = onRelease)

    LifecycleStartEffect(true) {
        currentOnInitializePlayer.invoke()
        onStopOrDispose {
            currentOnReleasePlayer.invoke()
        }
    }
}

@Composable
private fun KeepScreenOn() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val window = context.findActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        onDispose {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}

private fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) {
            return context
        }
        context = context.baseContext
    }
    throw IllegalStateException()
}