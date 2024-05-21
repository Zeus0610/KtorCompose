package com.zeus.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String
)
