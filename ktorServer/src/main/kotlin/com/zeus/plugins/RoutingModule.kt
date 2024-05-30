package com.zeus.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.zeus.data.repository.FilesRepository
import com.zeus.model.User
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.partialcontent.*
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
    install(PartialContent)

    val filesRepository = FilesRepository()

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
                    .withExpiresAt(Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(hashMapOf("token" to token))
            }

            get("/greetings") {
                call.respond(mapOf("message" to "greetings from server"))
            }

            route("/video") {
                get("/{content}/{chapter}/{file}") {
                    val content = call.parameters["content"]?: ""
                    val chapter = call.parameters["chapter"]?: ""
                    val fileName = call.parameters["file"]?: ""
                    val file = filesRepository.getFile("$content/$chapter/$fileName")
                    call.respondFile(file)
                }

                get("/{content}/{file}") {
                    val content = call.parameters["content"]?: ""
                    val fileName = call.parameters["file"]?: ""
                    val file = filesRepository.getFile("$content/$fileName")
                    call.respondFile(file)
                }
            }

            authenticate("auth-jwt") {
                get("/hello") {
                    call.respond("Is logged")
                }

                get("/home") {
                    try {
                        val content = filesRepository.getHomeContent()
                        call.respond(mapOf("result" to content))
                    } catch (e: Exception) {
                        call.respond(status = HttpStatusCode.InternalServerError, e.message.toString())
                    }
                }

                route("/content") {
                    get("/{content}") {
                        val content = call.parameters["content"]?: ""
                        val filesContent = filesRepository.getSingleContent(content)
                        call.respond(mapOf("result" to filesContent))
                    }
                }
            }
        }
    }
}