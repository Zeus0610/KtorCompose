package com.zeus.compose.ui.viewModels

import com.zeus.compose.ui.viewStates.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

actual fun getPlayerViewModel(): PlayerViewModel {
    return PlayerViewModelImpl()
}

class PlayerViewModelImpl: PlayerViewModel(){
    private var _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state

    fun initPlayer(url: String) {
        val player = EmbeddedMediaPlayerComponent("--no-video-title-show", "-vv", "--logfile=vlcj-log.txt")

        player.mediaPlayer().media().prepare(url)

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
        _state.value.player?.mediaPlayer()?.controls()?.play()
    }

    private fun pause() {
        _state.value.player?.mediaPlayer()?.controls()?.pause()
    }

    fun releasePlayer() {
        _state.value.player?.release()
        _state.value = _state.value.copy(
            player = null
        )
    }
}