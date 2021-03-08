package com.example.androiddevchallenge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun UseAnimation() {
    var animateState by remember { mutableStateOf(true) }
    val boxSize by animateDpAsState(targetValue = if (animateState) 50.dp else 100.dp)
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            animateState = animateState.not()
        }) {
            Text("Start animation")
        }
        AnimatedVisibility(visible = animateState) {
            Text("AnimatedVisibility")
        }
        Box(
            Modifier
                .background(Color.Cyan)
                .size(boxSize)
        ) {
            Text("animated Box size")
        }
    }
}

@Preview
@Composable
fun AnimationWithTransition() {
    var animationState by remember { mutableStateOf(true) }
    val transition = updateTransition(animationState)
    val animSize by transition.animateDp() {
        if (it) 60.dp else 120.dp
    }
    Box(
        Modifier.size(animSize)
            .clickable { animationState = animationState.not() }
            .background(Color.Green)
    )
}
