package com.zeus.compose.data.api

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import okhttp3.logging.HttpLoggingInterceptor

actual fun getClient(): HttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return HttpClient(OkHttp) {
        engine {
            addInterceptor(interceptor)
        }
    }
}
