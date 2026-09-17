package com.marcudlcv.dolist.data.remote.dto.task

import com.google.gson.annotations.SerializedName

// =========================
// RESPUESTA DE TAREA
// =========================

data class TaskDto(
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val dueDate: String?,
    val expired: Boolean = false,
    val createdAt: String?,
    val updatedAt: String?
)


// =========================
// CREAR TAREA
// =========================

data class CreateTaskRequest(
    val title: String,
    val description: String?,
    val priority: String,
    val dueDate: String?
)


// =========================
// ACTUALIZAR TAREA
// =========================

data class UpdateTaskRequest(
    val title: String,
    val description: String?,
    val priority: String,
    val status: String,
    val dueDate: String?
)

data class TaskListResponse(
    val tasks: List<TaskDto>
)

data class TaskResponse(
    val message: String?,
    val task: TaskDto?
)

data class MessageResponse(
    val message: String?
)