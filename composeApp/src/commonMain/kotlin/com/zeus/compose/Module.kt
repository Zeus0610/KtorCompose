package com.zeus.compose

import com.zeus.compose.data.repository.getHomeRepository
import com.zeus.compose.data.repository.getLoginRepository
import com.zeus.compose.domain.usecases.GetGreetingsUseCase
import com.zeus.compose.domain.usecases.LoginUseCase
import com.zeus.compose.ui.viewModels.HomeViewModel
import com.zeus.compose.ui.viewModels.LoginViewModel


object Module {
    private val homeRepository by lazy {
        getHomeRepository()
    }

    private val loginRepository by lazy {
        getLoginRepository()
    }

    private val greetingsUseCase by lazy {
        GetGreetingsUseCase(homeRepository)
    }

    private val loginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    fun getHomeViewModel(): HomeViewModel {
        return HomeViewModel(greetingsUseCase)
    }

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(loginUseCase)
    }
}