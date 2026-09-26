package com.marcudlcv.f1uigaragecompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcudlcv.f1uigaragecompose.ui.screens.HomeScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.PlaceholderScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.TextInputScreen

@Composable
fun GarageNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GarageRoutes.HOME
    ) {

        composable(GarageRoutes.HOME) {

            HomeScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(GarageRoutes.TEXT_INPUT) {
            TextInputScreen()
        }

        composable(GarageRoutes.BUTTONS) {
            PlaceholderScreen(
                title = "McLaren · Botones"
            )
        }

        composable(GarageRoutes.SELECTION) {
            PlaceholderScreen(
                title = "Mercedes · Selecciones"
            )
        }

        composable(GarageRoutes.LISTS) {
            PlaceholderScreen(
                title = "Williams · Listas"
            )
        }

        composable(GarageRoutes.FEEDBACK) {
            PlaceholderScreen(
                title = "Aston Martin · Retroalimentación"
            )
        }

        composable(GarageRoutes.LAYOUTS) {
            PlaceholderScreen(
                title = "Alpine · Layouts"
            )
        }
    }
}