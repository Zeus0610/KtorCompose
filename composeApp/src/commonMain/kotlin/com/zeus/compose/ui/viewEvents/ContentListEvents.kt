package com.zeus.compose.ui.viewEvents

sealed class ContentListEvents {
    data class OnInitScreen(val contentName: String): ContentListEvents()
}