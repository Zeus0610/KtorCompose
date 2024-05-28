package domain.usecases

import data.models.Greetings
import data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetGreetingsUseCase(
    val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<Greetings> {
        return homeRepository.getGreetings()
    }
}