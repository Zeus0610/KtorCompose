package com.zeus.compose.ui.screens

import androidx.compose.runtime.Composable
import com.zeus.compose.ui.viewModels.PlayerViewModel

@Composable
expect fun PlayerScreen(viewModel: PlayerViewModel,contentName: String, chapter: String, videoName: String)