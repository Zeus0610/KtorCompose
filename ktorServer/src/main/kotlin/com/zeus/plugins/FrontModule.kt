package com.zeus.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.frontModule() {
    routing {
        staticResources("/", "static/wasm") {
            preCompressed(CompressedFileType.GZIP)
        }
        staticResources("mani", "static") {
            default("mani.jpg")
            contentType {
                ContentType.Image.JPEG
            }
        }
    }
}