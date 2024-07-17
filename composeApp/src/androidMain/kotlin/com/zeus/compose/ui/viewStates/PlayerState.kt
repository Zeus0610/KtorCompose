package com.zeus.compose.ui.viewStates

import androidx.media3.common.Player

data class PlayerState(
    val player: Player? = null,
    val isPlaying: Boolean = false,
    val isOnFullscreen: Boolean = false,
)
