package com.zeus.compose.data.api

private const val URL_BASE = "http://localhost/api"

enum class EndPoints(val route: String) {
    GREETINGS("$URL_BASE/greetings"),
    LOGIN("$URL_BASE/login"),
    VALIDATE_SESSION("$URL_BASE/validateSession")
}