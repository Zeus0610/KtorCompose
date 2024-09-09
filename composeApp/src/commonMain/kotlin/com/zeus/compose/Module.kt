package com.zeus.compose

import com.zeus.compose.data.api.getEndPoints
import com.zeus.compose.data.repository.ContentRepositoryImpl
import com.zeus.compose.data.repository.LoginRepositoryImpl
import com.zeus.compose.domain.usecases.GetContentListUseCase
import com.zeus.compose.domain.usecases.GetHomeContentUseCase
import com.zeus.compose.domain.usecases.LoginUseCase
import com.zeus.compose.domain.usecases.ValidateSessionUseCase
import com.zeus.compose.persistence.repository.getSessionStorage
import com.zeus.compose.ui.viewModels.*
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

object Module {

    private val sessionStorageRepository by lazy {
        getEndPoints()
        getSessionStorage()
    }

    private val client by lazy {
        HttpClient().config {
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

    private val contentRepository by lazy {
        ContentRepositoryImpl(
            client,
            sessionStorageRepository
        )
    }

    private val loginRepository by lazy {
        LoginRepositoryImpl(
            client,
            sessionStorageRepository
        )
    }

    private val getHomeContentUseCase by lazy {
        GetHomeContentUseCase(contentRepository)
    }

    private val loginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    private val getContentListUseCase by lazy {
        GetContentListUseCase(contentRepository)
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

    fun getContentListViewModel(): ContentListViewModel {
        return ContentListViewModel(getContentListUseCase)
    }

    fun providePlayerViewModel(): PlayerViewModel {
        return getPlayerViewModel()
    }
}