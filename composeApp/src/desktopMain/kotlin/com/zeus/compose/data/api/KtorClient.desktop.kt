package com.zeus.compose.data.api

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun getClient(): HttpClient {
    return HttpClient(OkHttp)
}