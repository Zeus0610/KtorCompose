package com.zeus.compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.jsModules.dashjs
import kotlinx.browser.document
import kotlinx.dom.clear
import org.w3c.dom.HTMLVideoElement

@Composable
actual fun PlayerScreen(
    contentName: String,
    chapter: String,
    videoName: String
) {
    val player = remember { dashjs.MediaPlayer().create() }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )

    DisposableEffect(Unit) {
        println("params: $contentName $videoName")
        showDiv()
        if (chapter.isEmpty()) {
            initPlayer(player, EndPoints.videoUrl(contentName, videoName))
        } else {
            initPlayer(player, EndPoints.videoUrl(contentName, chapter, videoName))
        }

        onDispose {
            hideDiv()
            clearPlayer(player)
        }
    }
}

private fun initPlayer(player: dashjs.MediaPlayerClass, videoUrl: String) {
    if (videoUrl.contains("mpd")) {
        player.setXHRWithCredentialsForType("MediaSegment".toJsString(), true.toJsBoolean())
        player.initialize(getVideoElement(), videoUrl.toJsString(), true.toJsBoolean())
    } else {
        val videoElement = getVideoElement()
        val source = document.createElement("source")
        source.setAttribute("src", videoUrl)
        source.setAttribute("type", "video/mp4")

        videoElement.appendChild(source)
        videoElement.play()
    }
}

private fun clearPlayer(player: dashjs.MediaPlayerClass) {
    if (player.isReady().toBoolean()) {
        player.destroy()
    } else {
        val videoElement = getVideoElement()
        videoElement.pause()
        videoElement.clear()
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

private fun getVideoElement(): HTMLVideoElement {
    return document.querySelector("#videoPlayer") as HTMLVideoElement
}