package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun useScaffold() {
    Scaffold(
        topBar = {
            TopAppBar() {
                Text("TopBar content")
            }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }
                BottomNavigationItem(selected = true, onClick = {  }, icon = {}, label = {
                    Text("Tab1")
                })
                BottomNavigationItem(selected = false, onClick = {  }, icon = {}, label = {
                    Text("Tab2")
                })
            }
        }
    ) {

    }
}
