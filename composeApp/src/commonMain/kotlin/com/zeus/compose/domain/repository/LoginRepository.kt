package com.zeus.compose.domain.repository

import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(userCredentials: UserCredentialsDto): Flow<User>
    fun validateSession(): Flow<Boolean>
}