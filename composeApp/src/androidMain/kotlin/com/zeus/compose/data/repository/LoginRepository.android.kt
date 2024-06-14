package com.zeus.compose.data.repository

import com.zeus.compose.BuildConfig
import com.zeus.compose.data.RetrofitClient
import com.zeus.compose.data.RetrofitServices
import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.data.mappers.toUser
import com.zeus.compose.domain.models.User
import com.zeus.compose.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

actual fun getLoginRepository(): LoginRepository {
    val client = RetrofitClient.create(BuildConfig.URL_BASE)
    return LoginRepositoryImpl(client)
}

class LoginRepositoryImpl(
    val client: RetrofitServices
) : LoginRepository {

    override fun login(userCredentials: UserCredentialsDto): Flow<User> = flow {
        val response = client.login(userCredentials)
        emit(response.toUser())
    }.catch {
        it.printStackTrace()
    }

    override fun validateSession(): Flow<Boolean> = flow {
        val response = client.validateSession()
        emit(response)
    }.catch {
        emit(false)
    }
}