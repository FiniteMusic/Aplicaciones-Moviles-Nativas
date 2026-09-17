package com.marcudlcv.dolist.ui.components

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.marcudlcv.dolist.ui.theme.DarkAppBackground
import com.marcudlcv.dolist.ui.theme.DarkAppBackgroundMid
import com.marcudlcv.dolist.ui.theme.LightAppBackground
import com.marcudlcv.dolist.ui.theme.LightAppBackgroundMid
import com.marcudlcv.dolist.ui.theme.LocalDoListDarkTheme

@Composable
fun AuthBackground(
    content: @Composable BoxScope.() -> Unit
) {
    val isDark = LocalDoListDarkTheme.current

    val backgroundStart = if (isDark) {
        DarkAppBackground
    } else {
        LightAppBackground
    }

    val backgroundMid = if (isDark) {
        DarkAppBackgroundMid
    } else {
        LightAppBackgroundMid
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        backgroundStart,
                        backgroundMid,
                        backgroundStart
                    )
                )
            ),
        content = content
    )
}