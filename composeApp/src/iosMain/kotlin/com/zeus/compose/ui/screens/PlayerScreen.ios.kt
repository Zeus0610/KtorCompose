package com.zeus.compose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.ui.viewModels.PlayerViewModel
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlayerScreen(
    viewModel: PlayerViewModel,
    contentName: String,
    chapter: String,
    videoName: String
) {
    UIKitView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            AVPlayerViewController().apply {
                player = AVPlayer(uRL = NSURL(string = EndPoints.videoUrl(contentName, videoName.replace(".mpd", ".m3u8"))))
                player?.play()
            }.view
        }
    )
}