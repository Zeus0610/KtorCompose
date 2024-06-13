package com.zeus.compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String?,
    val token: String?
)
