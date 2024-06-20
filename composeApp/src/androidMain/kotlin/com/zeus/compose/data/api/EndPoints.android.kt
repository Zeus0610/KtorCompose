package com.zeus.compose.data.api

import com.zeus.compose.BuildConfig

actual fun getEndPoints(): EndPoints {
    return EndPoints.Builder(BuildConfig.URL_BASE).build()
}