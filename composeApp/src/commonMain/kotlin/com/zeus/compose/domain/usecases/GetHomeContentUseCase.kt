package com.zeus.compose.domain.usecases

import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow

class GetHomeContentUseCase(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): Flow<List<StreamingContent>> {
        return contentRepository.getHomeContent()
    }
}