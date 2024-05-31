package com.zeus.plugins

import com.zeus.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*

fun Application.frontModule() {
    routing {
        /*singlePageApplication {
            useResources = true
            react("spa")
        }*/
        staticResources("", "static") {
            default("index.html")
            preCompressed(CompressedFileType.GZIP)
        }
    }
}