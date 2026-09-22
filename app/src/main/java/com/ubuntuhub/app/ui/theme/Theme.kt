package com.ubuntuhub.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val UbuntuLightColorScheme = lightColorScheme(
    primary = UbuntuOrange,
    secondary = UbuntuGreen,
    background = UbuntuBackground,
    surface = UbuntuSurface,
    onPrimary = UbuntuSurface,
    onSecondary = UbuntuSurface,
    onBackground = UbuntuTextPrimary,
    onSurface = UbuntuTextPrimary,
    error = UbuntuError
)

@Composable
fun UbuntuHubTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = UbuntuLightColorScheme,
        typography = Typography,
        content = content
    )
}