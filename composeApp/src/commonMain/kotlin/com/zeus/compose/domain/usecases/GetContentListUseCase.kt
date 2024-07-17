package com.zeus.compose.domain.usecases

import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow

class GetContentListUseCase(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(contentName: String): Flow<List<StreamingContent>> {
        return contentRepository.getContentList(contentName)
    }
}