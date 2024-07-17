package com.zeus.compose.data.repository

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.data.dto.UserDto
import com.zeus.compose.data.mappers.toUser
import com.zeus.compose.domain.models.User
import com.zeus.compose.domain.repository.LoginRepository
import com.zeus.compose.domain.repository.SessionStorageRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
    private val client: HttpClient,
    private val sessionStorage: SessionStorageRepository
) : LoginRepository {

    override fun login(userCredentials: UserCredentialsDto): Flow<User> = flow {
        val response = client.post(EndPoints.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(userCredentials)
        }

        if (response.status.value in 200..299) {
            val userDto = response.body<UserDto>()
            sessionStorage.setToken(userDto.token)
            emit(userDto.toUser())
        }

    }.catch {
        it.printStackTrace()
    }

    override fun validateSession(): Flow<Boolean> = flow<Boolean> {
        val response = client.get(EndPoints.VALIDATE_SESSION) {
            header("Authorization",sessionStorage.getToken())
        }
        emit(response.body())
    }.catch {
        emit(false)
        it.cause
    }
}
