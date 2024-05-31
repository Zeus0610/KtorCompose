package com.zeus.compose.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeus.compose.domain.usecases.GetGreetingsUseCase
import kotlinx.coroutines.launch
import com.zeus.compose.ui.viewStates.HomeState

class HomeViewModel(
    private val getGreetingsUseCase: GetGreetingsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun getGreetings() = viewModelScope.launch {
        getGreetingsUseCase.invoke().collect {
            _state.value = _state.value.copy(text = it.message?: "")
        }
    }
}