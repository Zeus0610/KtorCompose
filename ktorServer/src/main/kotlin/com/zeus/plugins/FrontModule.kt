package com.zeus.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

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