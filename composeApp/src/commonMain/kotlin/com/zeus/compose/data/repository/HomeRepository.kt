package com.zeus.compose.data.repository

import com.zeus.compose.data.models.Greetings
import kotlinx.coroutines.flow.Flow

expect fun getHomeRepository(): HomeRepository

interface HomeRepository {
    suspend fun getGreetings(): Flow<Greetings>
}