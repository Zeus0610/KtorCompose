package com.zeus.compose.data.repository

import com.zeus.compose.data.models.User
import com.zeus.compose.data.models.UserCredentials
import kotlinx.coroutines.flow.Flow

expect fun getLoginRepository(): LoginRepository

interface LoginRepository {
    fun login(userCredentials: UserCredentials): Flow<User>
}