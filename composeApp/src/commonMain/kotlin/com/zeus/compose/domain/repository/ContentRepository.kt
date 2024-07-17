package com.zeus.compose.domain.repository

import com.zeus.compose.domain.models.StreamingContent
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getHomeContent(): Flow<List<StreamingContent>>
    fun getContentList(contentName: String): Flow<List<StreamingContent>>
}