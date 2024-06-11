package com.zeus.compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier

@Composable
actual fun PlayerScreen(
    videoUrl: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )

    DisposableEffect(Unit) {
        showDiv()
        initPlayer(videoUrl)

        onDispose {
            hideDiv()
        }
    }
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

private fun initPlayer(videoUrl: String) {
    js(
        """
            var url=videoUrl;
            var player=dashjs.MediaPlayer().create()
            player.setXHRWithCredentialsForType("MediaSegment",true)
            player.initialize(document.querySelector("#videoPlayer"), url, true);
        """
    )
}