package com.zeus.compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StreamingContentDto(
    val name: String?,
    val video: String?,
    val isSingleContent: Boolean?,
    val image: String?
)
