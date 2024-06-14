package com.zeus.compose.utils

import com.zeus.compose.BuildConfig
import com.zeus.compose.data.RetrofitClient
import com.zeus.compose.data.repository.LoginRepositoryImpl
import kotlinx.coroutines.flow.first

actual suspend fun hasSession(): Boolean {
    val client = RetrofitClient.create(BuildConfig.URL_BASE)
    val repository = LoginRepositoryImpl(client)
    return repository.validateSession().first()
}