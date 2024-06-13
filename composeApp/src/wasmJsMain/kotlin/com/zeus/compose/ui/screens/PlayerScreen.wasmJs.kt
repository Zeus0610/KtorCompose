package com.zeus.compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.zeus.compose.jsModules.dashjs
import kotlinx.browser.document

@Composable
actual fun PlayerScreen(
    videoUrl: String
) {
    val player = remember { dashjs.MediaPlayer().create() }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )

    DisposableEffect(Unit) {
        showDiv()
        initPlayer(player, videoUrl)

        onDispose {
            hideDiv()
            clearPlayer(player)
        }
    }
}

private fun initPlayer(player: dashjs.MediaPlayerClass,videoUrl: String) {
    player.setXHRWithCredentialsForType("MediaSegment".toJsString(), true.toJsBoolean())
    player.initialize(document.querySelector("#videoPlayer"), videoUrl.toJsString(), true.toJsBoolean())
}

private fun clearPlayer(player: dashjs.MediaPlayerClass) {
    player.destroy()
}

private fun hideDiv() {
    js(
        """
            document.getElementById("video_player_div").style.display = 'none'
        """
    )
}

private fun showDiv() {
    js(
        """
            document.getElementById("video_player_div").style.display = 'block'
        """
    )
}
