package com.marcudlcv.dolist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcudlcv.dolist.data.repository.TaskRepository
import com.marcudlcv.dolist.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TaskState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String? = null
)

class TaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _taskState = MutableStateFlow(TaskState())
    val taskState: StateFlow<TaskState> = _taskState.asStateFlow()

    fun loadTasks() {
        viewModelScope.launch {

            _taskState.value = TaskState(
                isLoading = true
            )

            val result = taskRepository.getTasks()

            result
                .onSuccess { tasks ->
                    _taskState.value = TaskState(
                        tasks = tasks
                    )
                }
                .onFailure { error ->
                    _taskState.value = TaskState(
                        error = error.message
                            ?: "No se pudieron cargar las tareas"
                    )
                }
        }
    }
    fun createTask(
        title: String,
        description: String,
        priority: com.marcudlcv.dolist.domain.model.Priority,
        dueDate: String?
    ) {
        viewModelScope.launch {

            val result = taskRepository.createTask(
                title = title,
                description = description,
                priority = priority,
                dueDate = dueDate
            )

            result
                .onSuccess {
                    loadTasks()
                }
                .onFailure { error ->
                    _taskState.value = _taskState.value.copy(
                        error = error.message
                            ?: "No se pudo crear la tarea"
                    )
                }
        }
    }

    fun updateTask(
        id: Int,
        title: String,
        description: String,
        priority: com.marcudlcv.dolist.domain.model.Priority,
        status: com.marcudlcv.dolist.domain.model.TaskStatus,
        dueDate: String?
    ) {
        viewModelScope.launch {
            val result = taskRepository.updateTask(
                id = id,
                title = title,
                description = description,
                priority = priority,
                status = status,
                dueDate = dueDate
            )

            result
                .onSuccess { updatedTask ->
                    _taskState.value = _taskState.value.copy(
                        tasks = _taskState.value.tasks.map { task ->
                            if (task.id == id) {
                                task.copy(
                                    status = updatedTask.status
                                )
                            } else {
                                task
                            }
                        },
                        error = null
                    )
                }
                .onFailure { error ->
                    _taskState.value = _taskState.value.copy(
                        error = error.message
                            ?: "No se pudo actualizar la tarea"
                    )
                }
        }
    }

    fun clearError() {
        _taskState.value = _taskState.value.copy(
            error = null
        )
    }
}