package com.zeus.compose.domain.usecases

import com.zeus.compose.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class ValidateSessionUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return loginRepository.validateSession()
    }
}