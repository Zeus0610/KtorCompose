package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.jetbrains.compose.ui.tooling.preview.Preview
import viewModels.HomeViewModel

@Preview
@Composable
fun LoginScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getGreetings()
    }

    Column {
        Text("This is a static text")
        Text(state.text)
    }
}