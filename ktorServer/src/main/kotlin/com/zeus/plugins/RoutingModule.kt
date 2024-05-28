package com.zeus.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.zeus.model.User
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.routingModule() {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()  //quitar despues
    }
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()

    routing {
        route("/api") {
            post("/login") {
                val user = call.receive<User>()

                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("username", user.username)
                    .withExpiresAt(Date(System.currentTimeMillis() + 120 * 1000))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(hashMapOf("token" to token))
            }

            get("/greetings") {
                call.respond(mapOf("message" to "greetings from server"))
            }

            authenticate("auth-jwt") {
                get("/hello") {
                    call.respond("Is logged")
                }
            }
        }
    }
}