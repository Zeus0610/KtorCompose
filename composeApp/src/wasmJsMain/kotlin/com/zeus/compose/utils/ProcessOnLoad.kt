package com.zeus.compose.utils

import com.zeus.compose.jsModules.JSON
import kotlinx.serialization.json.Json.Default.decodeFromString
import org.w3c.xhr.XMLHttpRequest

inline fun <reified T> XMLHttpRequest.processOnLoad(crossinline onSuccess: (T)->Unit, crossinline onError: () -> Unit = {}) {
    onload = {
        if (readyState == 4.toShort() && status == 200.toShort()) {
            val res = JSON.stringify(response)
            if (res != null) {
                val response = decodeFromString<T>(res)
                onSuccess.invoke(response)
            } else {
                onError.invoke()
            }
        }
    }
}