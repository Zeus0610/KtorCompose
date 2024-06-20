package com.zeus.compose.data.api

actual fun getEndPoints(): EndPoints {
    return EndPoints.Builder("http://localhost/api/").build()
}