package com.marcudlcv.dolist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDoListDarkTheme = staticCompositionLocalOf { false }

private val DoListDarkColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = DarkTextPrimary,

    secondary = Blue,
    onSecondary = DarkTextPrimary,

    tertiary = AccentLight,
    onTertiary = DarkTextPrimary,

    background = DarkAppBackground,
    onBackground = DarkTextPrimary,

    surface = DarkSurface,
    onSurface = DarkTextPrimary,

    surfaceVariant = DarkSurfaceStrong,
    onSurfaceVariant = DarkTextSecondary,

    error = ErrorColor,
    onError = DarkTextPrimary
)

private val DoListLightColorScheme = lightColorScheme(
    primary = AccentLightTheme,
    onPrimary = LightTextPrimary,

    secondary = BlueLightTheme,
    onSecondary = LightTextPrimary,

    tertiary = Accent,
    onTertiary = LightTextPrimary,

    background = LightAppBackground,
    onBackground = LightTextPrimary,

    surface = LightSurface,
    onSurface = LightTextPrimary,

    surfaceVariant = LightSurfaceStrong,
    onSurfaceVariant = LightTextSecondary,

    error = RedLightTheme,
    onError = LightTextPrimary
)

@Composable
fun DoListTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val systemDarkTheme = isSystemInDarkTheme()

    val darkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> systemDarkTheme
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = if (darkTheme) {
        DoListDarkColorScheme
    } else {
        DoListLightColorScheme
    }

    CompositionLocalProvider(
        LocalDoListDarkTheme provides darkTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = DoListTypography,
            shapes = DoListShapes,
            content = content
        )
    }
}