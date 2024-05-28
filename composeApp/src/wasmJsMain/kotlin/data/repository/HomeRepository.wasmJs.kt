package data.repository

import data.api.EndPoints
import data.models.Greetings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.json.Json.Default.decodeFromString
import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType

actual fun getHomeRepository(): HomeRepository {
    return HomeRepositoryImpl()
}

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getGreetings(): Flow<Greetings> = callbackFlow {
        val req = XMLHttpRequest()
        req.open("GET", EndPoints.GREETINGS.route, true)
        req.responseType = XMLHttpRequestResponseType.JSON
        req.send()
        req.onload = { _ ->
            if (req.readyState == 4.toShort() && req.status == 200.toShort()) {
                val res = JSON.stringify(req.response)
                if (res != null) {
                    val greetings: Greetings = decodeFromString(res)
                    trySend(greetings)
                }
            }
        }
        awaitClose {
            req.abort()
        }
    }
}

public external object JSON {
    public fun stringify(o: JsAny?): String?
}