package com.zeus.compose.ui.viewModels

import androidx.lifecycle.ViewModel

expect fun getPlayerViewModel(): PlayerViewModel

abstract class PlayerViewModel: ViewModel()