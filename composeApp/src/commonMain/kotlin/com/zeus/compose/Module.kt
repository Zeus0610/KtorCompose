package com.zeus.compose

import com.zeus.compose.data.api.getClient
import com.zeus.compose.data.api.getEndPoints
import com.zeus.compose.data.repository.HomeRepositoryImpl
import com.zeus.compose.data.repository.LoginRepositoryImpl
import com.zeus.compose.domain.usecases.GetHomeContentUseCase
import com.zeus.compose.domain.usecases.LoginUseCase
import com.zeus.compose.domain.usecases.ValidateSessionUseCase
import com.zeus.compose.persistence.repository.getSessionStorage
import com.zeus.compose.ui.viewModels.HomeViewModel
import com.zeus.compose.ui.viewModels.LoginViewModel
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

object Module {

    private val sessionStorageRepository by lazy {
        getSessionStorage()
    }

    private val client by lazy {
        getClient().config {
            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
        }
    }

    private val homeRepository by lazy {
        HomeRepositoryImpl(
            getEndPoints(),
            client,
            sessionStorageRepository
        )
    }

    private val loginRepository by lazy {
        LoginRepositoryImpl(
            getEndPoints(),
            client,
            sessionStorageRepository
        )
    }

    private val getHomeContentUseCase by lazy {
        GetHomeContentUseCase(homeRepository)
    }

    private val loginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    val validateSessionUseCase by lazy {
        ValidateSessionUseCase(loginRepository)
    }

    fun getHomeViewModel(): HomeViewModel {
        return HomeViewModel(getHomeContentUseCase)
    }

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(loginUseCase)
    }
}