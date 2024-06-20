package com.zeus.compose.domain.repository

interface SessionStorageRepository {
    fun getToken(): String
    fun setToken(token: String?)
}