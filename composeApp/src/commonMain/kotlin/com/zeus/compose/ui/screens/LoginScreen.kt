package com.zeus.compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.zeus.compose.ui.viewEvents.LoginEvents
import com.zeus.compose.ui.viewModels.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit = {},
) {
    val state = viewModel.state.value

    LaunchedEffect(state.user) {
        if (state.user != null) {
            navigateToHome.invoke()
        }
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            OutlinedTextField(
                value = state.username?: "",
                onValueChange = {
                    viewModel.onEvent(LoginEvents.OnSetUserName(it))
                },
                label = { Text("Usuario") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password?: "",
                onValueChange = {
                    viewModel.onEvent(LoginEvents.OnSetPassword(it))
                },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    viewModel.onEvent(LoginEvents.OnLogin)
                }
            ) {
                Text("Log in")
            }
        }
    }
}