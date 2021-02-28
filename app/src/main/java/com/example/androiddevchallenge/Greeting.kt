package com.example.androiddevchallenge

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Greeting(name: String) {
   var isSelected by remember { mutableStateOf(false) }
   val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

   Text(
       text = "Hello $name!",
       modifier = Modifier
           .padding(24.dp)
           .background(color = backgroundColor)
           .clickable(onClick = { isSelected = !isSelected })
   )
}
