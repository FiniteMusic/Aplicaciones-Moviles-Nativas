package com.marcudlcv.dolist.domain.model

enum class TaskStatus {
    PENDIENTE,
    COMPLETA
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TaskStatus,
    val dueDate: String?,
    val expired: Boolean = false,
    val createdAt: String = "",
    val updatedAt: String = ""
)