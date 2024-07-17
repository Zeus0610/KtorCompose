package com.zeus.model

import kotlinx.serialization.Serializable

@Serializable
data class StreamingContent(
    val name: String,
    val video: String? = "",
    val isSingleContent: Boolean = false,
    val image: String
)
