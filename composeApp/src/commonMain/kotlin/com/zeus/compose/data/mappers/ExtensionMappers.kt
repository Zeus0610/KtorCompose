package com.zeus.compose.data.mappers

import com.zeus.compose.data.dto.StreamingContentDto
import com.zeus.compose.data.dto.UserDto
import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.models.User

fun UserDto.toUser(): User {
    return User(
        name = this.name ?: "",
        token = this.token ?: ""
    )
}

fun StreamingContentDto.toStreamingContent(): StreamingContent {
    return StreamingContent(
        name = this.name ?: "",
        video = this.video ?: ""
    )
}