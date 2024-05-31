package com.zeus.compose.domain.usecases

import com.zeus.compose.data.models.User
import com.zeus.compose.data.models.UserCredentials
import com.zeus.compose.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    val loginRepository: LoginRepository
) {
    suspend operator fun invoke(username: String, password: String): Flow<User> {
        val userCredentials = UserCredentials(username, password)
        return loginRepository.login(userCredentials)
    }
}