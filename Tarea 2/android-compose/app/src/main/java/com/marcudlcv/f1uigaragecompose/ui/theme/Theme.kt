package com.marcudlcv.f1uigaragecompose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val GarageColorScheme = lightColorScheme(
    primary = Ferrari,
    secondary = GarageHeader,
    background = GarageBackground,
    surface = GarageSurface,
    onPrimary = GarageSurface,
    onSecondary = GarageSurface,
    onBackground = GarageTextPrimary,
    onSurface = GarageTextPrimary
)

@Composable
fun F1UIGarageComposeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GarageColorScheme,
        typography = Typography,
        content = content
    )
}