package com.zeus.compose.data.repository

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.models.User
import com.zeus.compose.data.models.UserCredentials
import kotlinx.browser.document
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

    override fun login(userCredentials: UserCredentials): Flow<User> = callbackFlow {
        val req = XMLHttpRequest()
        req.open("POST", EndPoints.LOGIN.route, true)
        req.responseType = XMLHttpRequestResponseType.JSON
        req.withCredentials = true
        req.setRequestHeader("Content-Type", "application/json")
        req.setRequestHeader("Access-Control-Allow-Origin", "*")

        req.onload = { _ ->
            if (req.readyState == 4.toShort() && req.status == 200.toShort()) {
                val res = JSON.stringify(req.response)
                if (res != null) {
                    val user: User = decodeFromString(res)
                    trySend(user)
                }
            }
        }
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

        req.onload = { _ ->
            if (req.readyState == 4.toShort() && req.status == 200.toShort()) {
                val res = req.responseText
                trySend(res.toBoolean())
            } else {
                trySend(false)
            }
        }

        req.send()

        awaitClose {
            req.abort()
        }
    }
}