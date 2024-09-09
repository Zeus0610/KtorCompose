package com.zeus.model

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val userToken: String
)
