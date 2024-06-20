package com.zeus.compose.data.api

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun getClient(): HttpClient {
    return HttpClient(Js)
}