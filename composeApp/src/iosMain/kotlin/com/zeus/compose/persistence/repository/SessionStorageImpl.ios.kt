package com.zeus.compose.persistence.repository

import com.zeus.compose.domain.repository.SessionStorageRepository

actual fun getSessionStorage(): SessionStorageRepository {
    return SessionStorageRepositoryImpl()
}

class SessionStorageRepositoryImpl: SessionStorageRepository {
    override fun getToken(): String {
        return ""
    }

    override fun setToken(token: String?) {

    }

}