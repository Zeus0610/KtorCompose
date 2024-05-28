package data.repository

import data.models.Greetings
import kotlinx.coroutines.flow.Flow

expect fun getHomeRepository(): HomeRepository

interface HomeRepository {
    suspend fun getGreetings(): Flow<Greetings>
}