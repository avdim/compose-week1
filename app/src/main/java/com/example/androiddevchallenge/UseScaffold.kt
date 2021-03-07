package com.example.androiddevchallenge

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun useScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("title")
            },
            navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            },
            actions = {
                Icon(imageVector = Icons.Default.ImageSearch, contentDescription = "")
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            })
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Adb, contentDescription = "")
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
