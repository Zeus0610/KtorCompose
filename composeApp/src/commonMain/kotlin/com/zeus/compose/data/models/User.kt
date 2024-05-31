package com.zeus.compose.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val token: String
)
