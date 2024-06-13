package com.zeus.compose.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeus.compose.domain.usecases.GetHomeContentUseCase
import com.zeus.compose.ui.viewEvents.HomeEvents
import kotlinx.coroutines.launch
import com.zeus.compose.ui.viewStates.HomeState

class HomeViewModel(
    private val getHomeContentUseCase: GetHomeContentUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnInitScreen -> getHomeContent()
        }
    }

    private fun getHomeContent() = viewModelScope.launch {
        getHomeContentUseCase.invoke().collect { listContent ->
            _state.value = _state.value.copy(
                isInitialized = true,
                content = listContent
            )
        }
    }
}