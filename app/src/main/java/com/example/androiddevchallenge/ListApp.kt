package com.example.androiddevchallenge

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

// Start building your app here!
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListApp(appState: AppState, onClick: () -> Unit = {}) {
    val border = animateDpAsState(10.dp + appState.count.dp * 2)
    Surface(color = MaterialTheme.colors.background) {
        Column(Modifier.fillMaxHeight()) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = appState.message,
                    modifier = Modifier
                        .border(4.dp, color = MaterialTheme.colors.primary)
                        .padding(24.dp)
                        .align(Alignment.End)
                )
                Card(
                    backgroundColor = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .width(80.dp)
                        .height(30.dp)
                ) {
                    Text("Card")
                }
                Divider(color = Color.Black)
                Box(
                    Modifier
                        .border(4.dp, color = MaterialTheme.colors.primary)
                        .padding(border.value)
                )
                LazyColumn {
                    val list = List(appState.count) { "element $it" }
                    list.chunked(5).forEachIndexed { index, dataItems ->
                        stickyHeader {
                            Card(backgroundColor = MaterialTheme.colors.secondary) {
                                Text("Section $index", style = MaterialTheme.typography.h3)
                            }
                        }
                        items(dataItems) {
                            Greeting(it)
                        }
                    }
                    repeat(appState.count / 5) { section ->

                    }
                }
            }
            Button(onClick = onClick) {
                Text("click me ${appState.count}")
            }
        }
    }
}


fun fakeState() = AppState("fake", 0)

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        ListApp(fakeState())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        ListApp(fakeState())
    }
}
