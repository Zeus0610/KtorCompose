package com.zeus.compose.ui.viewStates

import com.zeus.compose.data.models.User

data class LoginState(
    val loading: Boolean = false,
    val user: User? = null,
    val username: String? = null,
    val password: String? = null
)