package com.zeus.compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialsDto(
    val username: String,
    val password: String
)
