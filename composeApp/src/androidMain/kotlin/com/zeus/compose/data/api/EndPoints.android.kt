package com.zeus.compose.data.api

import com.zeus.compose.BuildConfig

actual fun getEndPoints() {
    return EndPoints.init(BuildConfig.URL_BASE)
}