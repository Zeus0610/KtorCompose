package com.zeus.compose

import com.zeus.compose.data.repository.getHomeRepository
import com.zeus.compose.data.repository.getLoginRepository
import com.zeus.compose.domain.usecases.GetHomeContentUseCase
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

    private val getHomeContentUseCase by lazy {
        GetHomeContentUseCase(homeRepository)
    }

    private val loginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    fun getHomeViewModel(): HomeViewModel {
        return HomeViewModel(getHomeContentUseCase)
    }

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(loginUseCase)
    }
}