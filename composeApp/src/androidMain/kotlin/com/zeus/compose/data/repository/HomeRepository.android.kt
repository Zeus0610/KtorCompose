package com.zeus.compose.data.repository

import com.zeus.compose.BuildConfig
import com.zeus.compose.data.RetrofitClient
import com.zeus.compose.data.RetrofitServices
import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.mappers.toStreamingContent
import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

actual fun getHomeRepository(): HomeRepository {
    val client = RetrofitClient.create(BuildConfig.URL_BASE)
    return HomeRepositoryImpl(client)
}

class HomeRepositoryImpl(
    private val client: RetrofitServices
) : HomeRepository {

    override fun getHomeContent(): Flow<List<StreamingContent>> = flow {
        val response = client.getHomeContent()
        emit(response.result.map { it.toStreamingContent() })
    }.catch {
        it.printStackTrace()
    }
}