package com.zeus.compose.ui.viewModels

actual fun getPlayerViewModel(): PlayerViewModel {
    return PlayerViewModelImpl()
}

class PlayerViewModelImpl : PlayerViewModel()