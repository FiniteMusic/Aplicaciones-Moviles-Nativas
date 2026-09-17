package com.marcudlcv.dolist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.marcudlcv.dolist.R
import com.marcudlcv.dolist.ui.theme.LocalDoListDarkTheme

@Composable
fun DoListLogo(
    modifier: Modifier = Modifier
) {
    val logoResource = if (LocalDoListDarkTheme.current) {
        R.drawable.dolist_logo_dark
    } else {
        R.drawable.dolist_logo_light
    }

    Image(
        painter = painterResource(id = logoResource),
        contentDescription = "DoList",
        modifier = modifier
    )
}