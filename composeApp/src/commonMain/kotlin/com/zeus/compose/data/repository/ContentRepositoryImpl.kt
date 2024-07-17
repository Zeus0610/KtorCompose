package com.zeus.compose.data.repository

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.dto.Result
import com.zeus.compose.data.dto.StreamingContentDto
import com.zeus.compose.data.mappers.toStreamingContent
import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.ContentRepository
import com.zeus.compose.domain.repository.SessionStorageRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ContentRepositoryImpl(
    private val client: HttpClient,
    private val sessionStorage: SessionStorageRepository
) : ContentRepository {

    override fun getHomeContent(): Flow<List<StreamingContent>> = flow {
        val response = client.get(EndPoints.HOME){
            header("Authorization", sessionStorage.getToken())
        }
        if (response.status.value in 200..299) {
            val contentList = response.body<Result<List<StreamingContentDto>>>().result
            emit(contentList.map { it.toStreamingContent() })
        }
    }.catch {
        it.printStackTrace()
    }

    override fun getContentList(contentName: String): Flow<List<StreamingContent>> = flow {
        val response = client.get(EndPoints.contentListURL(contentName)) {
            header("Authorization", sessionStorage.getToken())
        }

        if (response.status.value in 200..299) {
            val contentList = response.body<Result<List<StreamingContentDto>>>().result
            emit(contentList.map { it.toStreamingContent() })
        }
    }.catch {
        it.printStackTrace()
    }
}