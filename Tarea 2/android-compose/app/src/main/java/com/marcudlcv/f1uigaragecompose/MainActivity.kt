package com.marcudlcv.f1uigaragecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.marcudlcv.f1uigaragecompose.navigation.GarageNavigation
import com.marcudlcv.f1uigaragecompose.ui.theme.F1UIGarageComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            F1UIGarageComposeTheme {

                GarageNavigation()
            }
        }
    }
}