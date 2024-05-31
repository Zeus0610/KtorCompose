package com.zeus.compose.ui.viewEvents

sealed class LoginEvents {
    data class OnSetUserName(val username: String) : LoginEvents()
    data class OnSetPassword(val password: String) : LoginEvents()
    data object OnLogin : LoginEvents()
}