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
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
                Column {
                    CountDownApp()
                    ListApp(myState.value) {
                        updateState()
                    }
                }
            }
        }
    }
}

@Composable
fun CountDownApp() {
    var nums by remember { mutableStateOf(-1L) }
    remember {
        object : CountDownTimer(100_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                nums = millisUntilFinished / 100
            }

            override fun onFinish() {
                nums = 0L
            }
        }.start()
    }
    Text(text = "nums: $nums")
}
