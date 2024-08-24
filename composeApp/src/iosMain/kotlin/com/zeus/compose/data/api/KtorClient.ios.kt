package com.zeus.compose.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun getClient(): HttpClient {
    return HttpClient(Darwin)
}