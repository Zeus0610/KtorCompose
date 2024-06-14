package com.zeus.compose.data.api

object EndPoints {
    const val URL_BASE = "http://localhost/api/"
    const val LOGIN = "login"
    const val VALIDATE_SESSION = "validateSession"
    const val HOME = "home"

    fun String.withUrlBase(): String {
        return "$URL_BASE$this"
    }
}