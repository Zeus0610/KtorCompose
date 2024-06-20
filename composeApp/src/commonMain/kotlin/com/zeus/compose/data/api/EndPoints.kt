package com.zeus.compose.data.api

expect fun getEndPoints(): EndPoints

class EndPoints private constructor(
    urlBase: String
) {
    val LOGIN = urlBase + "login"
    val VALIDATE_SESSION = urlBase + "validateSession"
    val HOME = urlBase + "home"

    data class Builder(val urlBase: String) {
        fun build() = EndPoints(urlBase)
    }
}
