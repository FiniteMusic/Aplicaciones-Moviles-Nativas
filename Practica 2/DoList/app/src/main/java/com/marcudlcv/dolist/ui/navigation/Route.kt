package com.marcudlcv.dolist.ui.navigation

sealed class Route(val path: String) {

    // ─────────────────────────────────────────
    // Onboarding y autenticación
    // ─────────────────────────────────────────

    data object Onboarding : Route("onboarding")

    data object Login : Route("login")

    data object Register : Route("register")



    // ─────────────────────────────────────────
    // Aplicación principal
    // ─────────────────────────────────────────

    data object Home : Route("home")

    data object AllTasks : Route("all_tasks")

    data object Profile : Route("profile")


    // ─────────────────────────────────────────
    // Tareas
    // ─────────────────────────────────────────

    data class TaskDetail(
        val id: Int
    ) : Route("task_detail/$id")

    data class TaskForm(
        val id: Int? = null
    ) : Route(
        if (id == null) {
            "task_form"
        } else {
            "task_form?id=$id"
        }
    )


    // ─────────────────────────────────────────
    // Perfil y configuración
    // ─────────────────────────────────────────

    data object ChangeAvatar : Route("change_avatar")

    data object ChangePassword : Route("change_password")

    data object Settings : Route("settings")
}