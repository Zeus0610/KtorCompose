package com.zeus.compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Result<T>(
    val result: T
)
