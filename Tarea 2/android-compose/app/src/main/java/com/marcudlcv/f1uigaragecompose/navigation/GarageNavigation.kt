package com.marcudlcv.f1uigaragecompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcudlcv.f1uigaragecompose.ui.screens.HomeScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.PlaceholderScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.TextInputScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.ButtonsScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.SelectionScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.ListsScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.FeedbackScreen
import com.marcudlcv.f1uigaragecompose.ui.screens.LayoutsScreen

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
            ButtonsScreen()
        }

        composable(GarageRoutes.SELECTION) {
            SelectionScreen()
        }

        composable(GarageRoutes.LISTS) {
            ListsScreen()
        }

        composable(GarageRoutes.FEEDBACK) {
            FeedbackScreen()
        }

        composable(GarageRoutes.LAYOUTS) {
            LayoutsScreen()
        }
    }
}