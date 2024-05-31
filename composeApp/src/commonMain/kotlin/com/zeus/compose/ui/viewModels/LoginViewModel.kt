package com.zeus.compose.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeus.compose.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch
import com.zeus.compose.ui.viewEvents.LoginEvents
import com.zeus.compose.ui.viewStates.LoginState

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvents) {
        when(event) {
            is LoginEvents.OnLogin -> {
                if (_state.value.username.isNullOrBlank().not() && _state.value.password.isNullOrBlank().not()) {
                    login(
                        _state.value.username!!,
                        _state.value.password!!
                    )
                }
            }
            is LoginEvents.OnSetPassword -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }
            is LoginEvents.OnSetUserName -> {
                _state.value = _state.value.copy(
                    username = event.username
                )
            }
        }
    }

    private fun login(username: String, password: String) = viewModelScope.launch {
        loginUseCase.invoke(username, password).collect { user ->
            _state.value = _state.value.copy(
                user = user
            )
        }
    }
}