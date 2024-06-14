package com.zeus.compose.data.repository

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.api.EndPoints.withUrlBase
import com.zeus.compose.data.dto.Result
import com.zeus.compose.data.dto.StreamingContentDto
import com.zeus.compose.data.mappers.toStreamingContent
import com.zeus.compose.domain.models.StreamingContent
import com.zeus.compose.domain.repository.HomeRepository
import com.zeus.compose.utils.processOnLoad
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType

actual fun getHomeRepository(): HomeRepository {
    return HomeRepositoryImpl()
}

class HomeRepositoryImpl : HomeRepository {

    override fun getHomeContent(): Flow<List<StreamingContent>> = callbackFlow {
        val req = XMLHttpRequest()
        req.open("GET", EndPoints.HOME.withUrlBase(), true)
        req.responseType = XMLHttpRequestResponseType.JSON
        //req.setRequestHeader("Access-Control-Allow-Origin", "*")
        req.withCredentials = true

        req.processOnLoad<Result<List<StreamingContentDto>>>(
            onSuccess = { response ->
                trySend(response.result.map { it.toStreamingContent() })
            }
        )

        req.send()

        awaitClose {
            req.abort()
        }
    }
}