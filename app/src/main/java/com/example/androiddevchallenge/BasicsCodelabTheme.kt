package com.example.androiddevchallenge

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.purple200
import com.example.androiddevchallenge.ui.theme.purple500
import com.example.androiddevchallenge.ui.theme.purple700
import com.example.androiddevchallenge.ui.theme.teal200


private val DarkColors = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColors = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200
)

@Composable
fun BasicsCodelabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors: Colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(colors = colors) {
        content()
    }
}
