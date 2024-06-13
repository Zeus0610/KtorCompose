package com.zeus.compose.domain.repository

import com.zeus.compose.domain.models.StreamingContent
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeContent(): Flow<List<StreamingContent>>
}