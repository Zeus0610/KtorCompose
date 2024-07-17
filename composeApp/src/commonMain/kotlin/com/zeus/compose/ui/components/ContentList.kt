package com.zeus.compose.ui.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zeus.compose.domain.models.StreamingContent
import ktorcompose.composeapp.generated.resources.Res
import ktorcompose.composeapp.generated.resources.ic_launcher_foreground
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentList(
    title: String = "",
    contentList: List<StreamingContent> = emptyList(),
    onContentClick: (isSingleContent: Boolean, contentName: String, videoName: String) -> Unit = { _, _, _ -> }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            columns = GridCells.Fixed(3)
        ) {
            items(items = contentList) { content ->
                Box(
                    modifier = Modifier
                        .clickable {
                            onContentClick.invoke(content.isSingleContent, content.name, content.video)
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier.defaultMinSize(200.dp, 200.dp),
                        model = content.image,
                        error = painterResource(Res.drawable.ic_launcher_foreground),
                        contentDescription = content.name,
                        onError = {
                            it.result.throwable.printStackTrace()
                        }
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        text = content.name,
                    )
                }
            }
        }
    }
}