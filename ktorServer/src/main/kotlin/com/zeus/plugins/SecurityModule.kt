package com.zeus.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.zeus.model.UserSession
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.util.*

fun Application.securityModule() {
    install(CORS) {
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHost("0.0.0.0:8080")
        allowHost("localhost:8080")
        anyHost() //quitar despues
        allowCredentials = true
    }
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val mRealm = environment.config.property("jwt.realm").getString()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = mRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )

            authHeader { call ->
                val authorization = call.request.headers["Authorization"]
                if (authorization.isNullOrEmpty()) {
                    val session = call.sessions.get<UserSession>()

                    try {
                        parseAuthorizationHeader("Bearer ${session?.userToken}")
                    } catch (e: IllegalArgumentException) {
                        e.message
                        null
                    }
                } else {
                    parseAuthorizationHeader(authorization)
                }
            }

            validate { credential ->
                if (credential.payload.getClaim("username").asString().isNotBlank()) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is no valid or has expired")
            }
        }
    }

    val sessionSecretEncryptKey = environment.config.property("session.secretEncryptKey").getString()
    val sessionSecretSignKey = environment.config.property("session.secretSignKey").getString()
    install(Sessions) {
        val secretEncryptKey = hex(sessionSecretEncryptKey)
        val secretSignKey = hex(sessionSecretSignKey)
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }
}