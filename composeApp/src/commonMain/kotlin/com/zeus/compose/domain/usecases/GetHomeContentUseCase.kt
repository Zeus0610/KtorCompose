package com.zeus.compose.domain.usecases

import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeContentUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<List<StreamingContent>> {
        return homeRepository.getHomeContent()
    }
}