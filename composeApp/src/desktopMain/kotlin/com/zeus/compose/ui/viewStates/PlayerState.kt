package com.zeus.compose.ui.viewStates

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

data class PlayerState(
    val player: EmbeddedMediaPlayerComponent? = null,
    val isPlaying: Boolean = false,
    val isOnFullscreen: Boolean = false,
)
