package com.zeus.compose.domain.usecases

import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.data.mappers.toUser
import com.zeus.compose.domain.models.User
import com.zeus.compose.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginUseCase(
    val loginRepository: LoginRepository
) {
    operator fun invoke(username: String, password: String): Flow<User> {
        val userCredentials = UserCredentialsDto(username, password)
        return loginRepository.login(userCredentials)
    }
}