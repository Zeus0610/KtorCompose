package com.zeus.compose.utils

import kotlinx.browser.document

actual fun hasSession(): Boolean {
    return getCookie("user_session").isNullOrBlank().not()
}

private fun getCookie(name: String): String? {
    val value = document.cookie
    val parts = value.split("$name=")
    return if (parts.size == 2) {
        parts[1].split(";").first()
    } else {
        null
    }
}