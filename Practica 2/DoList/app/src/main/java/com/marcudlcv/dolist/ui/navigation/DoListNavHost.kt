package com.marcudlcv.dolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.marcudlcv.dolist.DoListApplication
import com.marcudlcv.dolist.ui.components.BottomNavDestination
import com.marcudlcv.dolist.ui.screens.auth.LoginScreen
import com.marcudlcv.dolist.ui.screens.auth.LoginState
import com.marcudlcv.dolist.ui.screens.auth.RegisterScreen
import com.marcudlcv.dolist.ui.screens.auth.AuthViewModel
import com.marcudlcv.dolist.ui.screens.auth.AuthViewModelFactory
import com.marcudlcv.dolist.ui.screens.home.HomeScreen
import com.marcudlcv.dolist.ui.screens.onboarding.OnboardingScreen
import com.marcudlcv.dolist.ui.screens.profile.ProfileScreen
import com.marcudlcv.dolist.ui.screens.tasks.AllTasksScreen
import com.marcudlcv.dolist.ui.theme.ThemeMode
import com.marcudlcv.dolist.ui.viewmodel.TaskViewModel
import com.marcudlcv.dolist.ui.viewmodel.TaskViewModelFactory

@Composable
fun DoListNavHost(
    navController: NavHostController,
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit,
    startDestination: String = Route.Onboarding.path
) {
    val context = LocalContext.current

    val application = context.applicationContext as DoListApplication

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(
            application.container.authRepository
        )
    )
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(
            application.container.taskRepository
        )
    )

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ═════════════════════════════════════
        // ONBOARDING
        // ═════════════════════════════════════

        composable(
            route = Route.Onboarding.path
        ) {

            OnboardingScreen(
                onGetStarted = {

                    navController.navigate(
                        Route.Login.path
                    ) {
                        popUpTo(
                            Route.Onboarding.path
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        // ═════════════════════════════════════
        // LOGIN
        // ═════════════════════════════════════

        composable(
            route = Route.Login.path
        ) {
            val loginState by authViewModel.loginState.collectAsState()

            LaunchedEffect(loginState) {
                if (loginState is LoginState.Success) {

                    navController.navigate(
                        Route.Home.path
                    ) {
                        popUpTo(
                            Route.Login.path
                        ) {
                            inclusive = true
                        }
                    }

                    authViewModel.resetLoginState()
                }
            }

            LoginScreen(
                onLogin = { email, password ->
                    authViewModel.login(
                        email = email,
                        password = password
                    )
                },

                onRegister = {
                    navController.navigate(
                        Route.Register.path
                    )
                },

                errorMessage = when (val state = loginState) {
                    is LoginState.Error -> state.message
                    else -> null
                },

                isLoading = loginState is LoginState.Loading
            )
        }


        // ═════════════════════════════════════
        // REGISTER
        // ═════════════════════════════════════

        composable(Route.Register.path) {

            val registerState by authViewModel.loginState.collectAsState()

            LaunchedEffect(registerState) {
                when (registerState) {

                    is LoginState.Success -> {

                        navController.navigate(
                            Route.Home.path
                        ) {
                            popUpTo(
                                Route.Register.path
                            ) {
                                inclusive = true
                            }
                        }

                        authViewModel.resetLoginState()
                    }

                    else -> Unit
                }
            }

            RegisterScreen(
                onRegister = { name, email, password ->
                    authViewModel.register(
                        name = name,
                        email = email,
                        password = password
                    )
                },

                onBackToLogin = {
                    navController.popBackStack()
                },

                errorMessage = when (val state = registerState) {
                    is LoginState.Error -> state.message
                    else -> null
                },

                isLoading = registerState is LoginState.Loading
            )
        }


        // ═════════════════════════════════════
        // HOME
        // ═════════════════════════════════════

        composable(
            route = Route.Home.path
        ) {

            // Estado del usuario autenticado
            val userState by authViewModel.userState.collectAsState()
            val taskState by taskViewModel.taskState.collectAsState()

            // Obtener información del usuario desde /api/auth/me
            LaunchedEffect(Unit) {
                if (userState.user == null) {
                    authViewModel.loadCurrentUser()
                }

                taskViewModel.loadTasks()
            }

            HomeScreen(
                userName = userState.user?.name ?: "Usuario",
                tasks = taskState.tasks,

                onCreateTaskWithData = { title, description, priority, dueDate ->
                    taskViewModel.createTask(
                        title = title,
                        description = description,
                        priority = priority,
                        dueDate = dueDate
                    )
                },
                onUpdateTask = { id, title, description, priority, status, dueDate ->
                    taskViewModel.updateTask(
                        id = id,
                        title = title,
                        description = description,
                        priority = priority,
                        status = status,
                        dueDate = dueDate
                    )
                },

                onTaskClick = { taskId ->

                    // Próximamente:
                    // abrir detalle de la tarea
                },

                onNavigate = { destination ->

                    when (destination) {

                        BottomNavDestination.HOME -> {
                            // Ya estamos aquí.
                        }

                        BottomNavDestination.ALL_TASKS -> {
                            navController.navigate(
                                Route.AllTasks.path
                            ) {
                                launchSingleTop = true
                            }
                        }

                        BottomNavDestination.PROFILE -> {
                            navController.navigate(
                                Route.Profile.path
                            ) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }


        // ═════════════════════════════════════
        // TODAS MIS TAREAS
        // ═════════════════════════════════════

        composable(
            route = Route.AllTasks.path
        ) {

            AllTasksScreen(

                onTaskClick = { taskId ->

                    // Próximamente:
                    // abrir detalle de la tarea
                },

                onNavigate = { destination ->

                    when (destination) {

                        // ─────────────────────
                        // HOME
                        // ─────────────────────

                        BottomNavDestination.HOME -> {

                            navController.navigate(
                                Route.Home.path
                            ) {
                                launchSingleTop = true
                            }
                        }


                        // ─────────────────────
                        // TODAS MIS TAREAS
                        // ─────────────────────

                        BottomNavDestination.ALL_TASKS -> {

                            // Ya estamos aquí.
                        }


                        // ─────────────────────
                        // PERFIL
                        // ─────────────────────

                        BottomNavDestination.PROFILE -> {

                            navController.navigate(
                                Route.Profile.path
                            ) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }


        // ═════════════════════════════════════
        // PERFIL
        // ═════════════════════════════════════

        composable(
            route = Route.Profile.path
        ) {

            ProfileScreen(
                themeMode = themeMode,
                onThemeModeChange = onThemeModeChange,

                onNavigate = { destination ->

                    when (destination) {

                        // ─────────────────────
                        // HOME
                        // ─────────────────────

                        BottomNavDestination.HOME -> {

                            navController.navigate(
                                Route.Home.path
                            ) {
                                launchSingleTop = true
                            }
                        }


                        // ─────────────────────
                        // TODAS MIS TAREAS
                        // ─────────────────────

                        BottomNavDestination.ALL_TASKS -> {

                            navController.navigate(
                                Route.AllTasks.path
                            ) {
                                launchSingleTop = true
                            }
                        }


                        // ─────────────────────
                        // PERFIL
                        // ─────────────────────

                        BottomNavDestination.PROFILE -> {

                            // Ya estamos aquí.
                        }
                    }
                },

                onChangeAvatar = {

                    // Próximamente:
                    // Route.ChangeAvatar
                },

                onChangePassword = {

                    // Próximamente:
                    // Route.ChangePassword
                },

                onSettings = {

                    // Próximamente:
                    // Route.Settings
                },

                onLogout = {

                    // Próximamente:
                    // cerrar sesión
                }
            )
        }
    }
}