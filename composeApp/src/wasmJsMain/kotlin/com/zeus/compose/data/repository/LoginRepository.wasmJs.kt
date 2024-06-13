package com.zeus.compose.data.repository

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.dto.UserDto
import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.data.mappers.toUser
import com.zeus.compose.domain.models.User
import com.zeus.compose.domain.repository.LoginRepository
import com.zeus.compose.jsModules.JSON
import com.zeus.compose.utils.processOnLoad
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import org.w3c.xhr.JSON
import org.w3c.xhr.TEXT
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType

actual fun getLoginRepository(): LoginRepository {
    return LoginRepositoryImpl()
}

class LoginRepositoryImpl: LoginRepository {

    override fun login(userCredentials: UserCredentialsDto): Flow<User> = callbackFlow {
        val req = XMLHttpRequest()
        req.open("POST", EndPoints.LOGIN.route, true)
        req.responseType = XMLHttpRequestResponseType.JSON
        req.withCredentials = true
        req.setRequestHeader("Content-Type", "application/json")
        req.setRequestHeader("Access-Control-Allow-Origin", "*")

        req.processOnLoad<UserDto>(
            onSuccess = { response ->
                trySend(response.toUser())
            }
        )

        val credentialsJson = Json.encodeToString(userCredentials)
        req.send(credentialsJson)

        awaitClose {
            req.abort()
        }
    }

    override fun validateSession(): Flow<Boolean> = callbackFlow {
        val req = XMLHttpRequest()
        req.open("GET", EndPoints.VALIDATE_SESSION.route, true)
        req.responseType = XMLHttpRequestResponseType.TEXT
        req.withCredentials = true

        req.processOnLoad<Boolean>(
            onSuccess = { response ->
                trySend(response)
            },
            onError = {
                trySend(false)
            }
        )

        req.send()

        awaitClose {
            req.abort()
        }
    }
}