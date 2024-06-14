package com.zeus.compose.data

import android.se.omapi.Session
import android.webkit.CookieManager
import com.zeus.compose.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.net.CookieStore

object RetrofitClient {

    private var client: RetrofitServices? = null

    fun create(baseUrl: String): RetrofitServices {
        if (client == null) {
            val retrofit = createRetrofit(BuildConfig.DEBUG, baseUrl)
            client = retrofit.create(RetrofitServices::class.java)
            return client!!
        } else {
            return client!!
        }
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOKHttpClient(createInterceptor(isDebug)))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun createOKHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cookieJar(SessionCookieJar())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun createInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}

class SessionCookieJar : CookieJar {

    private val cookieManager = CookieManager.getInstance()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val session = cookieManager.getCookie(url.toString())
        if (session != null) {
            return listOf(Cookie.parse(url, session)!!)
        } else {
            return emptyList()
        }
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val session = cookies.find { it.name == "user_session" }
        if (session != null) {
            cookieManager.setCookie(url.toString(), session.toString())
        }
    }
}