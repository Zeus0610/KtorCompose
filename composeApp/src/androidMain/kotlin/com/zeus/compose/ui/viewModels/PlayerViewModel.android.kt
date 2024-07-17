package com.zeus.compose.ui.viewModels

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.zeus.compose.ui.viewStates.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual fun getPlayerViewModel(): PlayerViewModel {
    return PlayerViewModelImpl()
}

class PlayerViewModelImpl: PlayerViewModel() {
    private var _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _state.value = _state.value.copy(isPlaying =  isPlaying)
        }
    }

    fun initPlayer(url: String, context: Context) {
        val player = ExoPlayer.Builder(context.applicationContext)
            .build()
        player.setMediaItem(MediaItem.fromUri(url))
        player.addListener(playerListener)
        player.prepare()

        _state.value = _state.value.copy(player = player)
    }

    fun playPause() {
        if (_state.value.isPlaying) {
            pause()
        } else {
            play()
        }
    }

    private fun play() {
        _state.value.player?.play()
    }

    private fun pause() {
        _state.value.player?.pause()
    }

    fun releasePlayer() {
        _state.value.player?.release()
        _state.value = _state.value.copy(
            player = null
        )
    }
}