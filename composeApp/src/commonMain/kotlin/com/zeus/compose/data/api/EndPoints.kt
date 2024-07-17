package com.zeus.compose.data.api

expect fun getEndPoints()

object EndPoints {
    var urlBase: String = ""
    val LOGIN
        get() = urlBase + "login"
    val VALIDATE_SESSION
        get() = urlBase + "validateSession"
    val HOME
        get() = urlBase + "home"

    private val VIDEO = "video/"
    private val CONTENT = "content/"

    fun init(urlBase: String) {
        this.urlBase = urlBase
    }

    fun videoUrl(contentName: String, videoUrl: String): String {
        return "$urlBase$VIDEO$contentName/$videoUrl"
    }

    fun videoUrl(contentName: String, chapter: String, videoUrl: String): String {
        return "$urlBase$VIDEO$contentName/$chapter/$videoUrl"
    }

    fun contentListURL(contentName: String): String {
        return "$urlBase$CONTENT/$contentName"
    }

    fun imageUrl(): String {
        return "$urlBase$VIDEO/"
    }
}
