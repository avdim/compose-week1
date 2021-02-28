/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.flow.MutableStateFlow

data class AppState(
    val message: String,
    val count: Int
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mutableStateFlow = MutableStateFlow(
            AppState("hello compose", 0)
        )

        fun updateState() {
            val old = mutableStateFlow.value
            mutableStateFlow.value = old
                .copy(
                    count = old.count + 1
                )
        }

        setContent {
            val myState = mutableStateFlow.collectAsState()
            MyTheme {
                MyApp(myState.value) {
                    updateState()
                }
            }
        }
    }
}

// Start building your app here!
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyApp(appState: AppState, onClick: () -> Unit = {}) {
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
                                Text("Section $index")
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
        MyApp(fakeState())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(fakeState())
    }
}
