package com.marcudlcv.dolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.marcudlcv.dolist.ui.navigation.DoListNavHost
import com.marcudlcv.dolist.ui.theme.DoListTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.marcudlcv.dolist.ui.theme.ThemeMode
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.marcudlcv.dolist.data.preferences.ThemePreferences
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DoListApp()
        }
    }
}

@Composable
private fun DoListApp() {
    val context = LocalContext.current

    val themePreferences = remember {
        ThemePreferences(context)
    }

    val themeMode by themePreferences.themeMode.collectAsState(
        initial = ThemeMode.SYSTEM
    )

    val scope = rememberCoroutineScope()

    DoListTheme(
        themeMode = themeMode
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val navController = rememberNavController()

            DoListNavHost(
                navController = navController,
                themeMode = themeMode,
                onThemeModeChange = { newThemeMode ->
                    scope.launch {
                        themePreferences.saveThemeMode(newThemeMode)
                    }
                }
            )
        }
    }
}