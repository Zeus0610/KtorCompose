package com.zeus.compose.domain.usecases

import com.zeus.compose.data.models.Greetings
import com.zeus.compose.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetGreetingsUseCase(
    val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<Greetings> {
        return homeRepository.getGreetings()
    }
}