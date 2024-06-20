package com.zeus.compose.persistence.repository

import com.zeus.compose.domain.repository.SessionStorageRepository
import kotlinx.browser.localStorage

actual fun getSessionStorage(): SessionStorageRepository {
    return SessionStorageRepositoryImpl()
}

class SessionStorageRepositoryImpl : SessionStorageRepository {
    override fun getToken(): String {
        return localStorage.getItem("sessionToken")?: ""
    }

    override fun setToken(token: String?) {
        token?.let {
            localStorage.setItem("sessionToken", "Bearer $it")
        }
    }
}