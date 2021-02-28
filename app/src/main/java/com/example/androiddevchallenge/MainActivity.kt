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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
@Composable
fun MyApp(appState: AppState, onClick: () -> Unit = {}) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Text(
                text = appState.message,
                modifier = Modifier
                    .border(4.dp, color = MaterialTheme.colors.primary)
                    .padding(24.dp)
            )
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
