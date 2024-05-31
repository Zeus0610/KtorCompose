package com.zeus.compose.utils

import com.zeus.compose.data.repository.getLoginRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single

actual suspend fun hasSession(): Boolean {
    println("Welcome Back")
    val repo = getLoginRepository()
    val res = repo.validateSession().first()
    println("hasSession: $res")
    return res
}
