package com.marcudlcv.dolist.data.repository

import com.marcudlcv.dolist.data.remote.api.ApiService
import com.marcudlcv.dolist.data.remote.dto.task.TaskDto
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.domain.model.Task
import com.marcudlcv.dolist.domain.model.TaskStatus
import com.marcudlcv.dolist.data.remote.dto.task.CreateTaskRequest
import com.marcudlcv.dolist.data.remote.dto.task.UpdateTaskRequest

class TaskRepository(
    private val apiService: ApiService
) {

    suspend fun getTasks(): Result<List<Task>> {
        return try {
            val response = apiService.getTasks()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.success(
                        body.tasks.map { it.toDomain() }
                    )
                } else {
                    Result.failure(
                        Exception("El servidor no devolvió las tareas")
                    )
                }
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "No se pudieron obtener las tareas"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createTask(
        title: String,
        description: String,
        priority: Priority,
        dueDate: String?
    ): Result<Task> {
        return try {
            val response = apiService.createTask(
                CreateTaskRequest(
                    title = title,
                    description = description.ifBlank { null },
                    priority = priority.name.lowercase(),
                    dueDate = dueDate
                )
            )

            if (response.isSuccessful) {
                val body = response.body()

                if (body?.task != null) {
                    Result.success(
                        body.task.toDomain()
                    )
                } else {
                    Result.failure(
                        Exception("El servidor no devolvió la tarea creada")
                    )
                }
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "No se pudo crear la tarea"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun updateTask(
        id: Int,
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
        dueDate: String?
    ): Result<Task> {
        return try {
            val response = apiService.updateTask(
                id = id,
                request = UpdateTaskRequest(
                    title = title,
                    description = description.ifBlank { null },
                    priority = priority.name.lowercase(),
                    status = when (status) {
                        TaskStatus.PENDIENTE -> "pendiente"
                        TaskStatus.COMPLETA -> "completada"
                    },
                    dueDate = dueDate
                )
            )

            if (response.isSuccessful) {
                val body = response.body()

                if (body?.task != null) {
                    Result.success(body.task.toDomain())
                } else {
                    Result.failure(
                        Exception("El servidor no devolvió la tarea actualizada")
                    )
                }
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "No se pudo actualizar la tarea"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun TaskDto.toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            priority = when (priority.lowercase()) {
                "alta" -> Priority.ALTA
                "media" -> Priority.MEDIA
                else -> Priority.BAJA
            },
            status = when (status.lowercase()) {
                "completa" -> TaskStatus.COMPLETA
                else -> TaskStatus.PENDIENTE
            },
            dueDate = dueDate,
            expired = expired,
            createdAt = createdAt ?: "",
            updatedAt = updatedAt ?: ""
        )
    }
}