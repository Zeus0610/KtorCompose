package com.zeus.compose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zeus.compose.ui.viewEvents.HomeEvents
import com.zeus.compose.ui.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onContentClick: (String) -> Unit = {}
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        if (state.isInitialized.not()) {
            viewModel.onEvent(HomeEvents.OnInitScreen)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            columns = GridCells.Fixed(3)
        ) {
            items(items = state.content) { content ->
                Box(
                    modifier = Modifier
                        .clickable {
                            //todo
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier.defaultMinSize(500.dp, 500.dp),
                        model = "https://coil-kt.github.io/coil/images/coil_logo_black.svg",
                        contentDescription = content.name,
                        colorFilter = ColorFilter.tint(Color.Cyan)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                        ,
                        text = content.name,
                    )
                }
            }
        }
        /*Column(
            modifier = Modifier.padding(padding)
        ) {
            Text("Image home Screen with top bar")
            Button(
                onClick = {
                    onContentClick.invoke("Halo Fall of Reach")
                }
            ) {
                Text("Open player")
            }
        }*/
    }
}