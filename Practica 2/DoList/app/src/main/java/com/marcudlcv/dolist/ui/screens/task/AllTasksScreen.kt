package com.marcudlcv.dolist.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.domain.model.Task
import com.marcudlcv.dolist.domain.model.TaskStatus
import com.marcudlcv.dolist.ui.components.BottomNavDestination
import com.marcudlcv.dolist.ui.components.GlassBottomNav
import com.marcudlcv.dolist.ui.components.GlassSurface
import com.marcudlcv.dolist.ui.components.TaskCard
import com.marcudlcv.dolist.ui.navigation.Route
import com.marcudlcv.dolist.ui.screens.tasks.DeleteTaskScreen
import com.marcudlcv.dolist.ui.screens.tasks.TaskFormScreen

@Composable
fun AllTasksScreen(
    onTaskClick: (Int) -> Unit = {},
    onCreateTask: () -> Unit = {},
    onEditTask: (Int) -> Unit = {},
    onDeleteTask: (Int) -> Unit = {},
    onNavigate: (BottomNavDestination) -> Unit = {}
) {

    var currentDestination by remember {
        mutableStateOf(BottomNavDestination.ALL_TASKS)
    }

    var showTaskForm by remember {
        mutableStateOf(false)
    }

    var taskToEdit by remember {
        mutableStateOf<Task?>(null)
    }

    var taskToDelete by remember {
        mutableStateOf<Task?>(null)
    }

    // ─────────────────────────────────
    // Filtro seleccionado
    //
    // 0 = Todas
    // 1 = Pendientes
    // 2 = Completadas
    // ─────────────────────────────────

    var selectedFilter by remember {
        mutableIntStateOf(0)
    }

    // ─────────────────────────────────
    // Datos temporales
    // ─────────────────────────────────

    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(
                    id = 1,
                    title = "Revisar pull requests del equipo",
                    description = "Revisar y aprobar los PRs pendientes en GitHub...",
                    priority = Priority.ALTA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "12 sep"
                ),

                Task(
                    id = 2,
                    title = "Actualizar documentación API",
                    description = "Añadir los nuevos endpoints de autenticación al...",
                    priority = Priority.MEDIA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "12 sep"
                ),

                Task(
                    id = 3,
                    title = "Llamar al dentista",
                    description = "Pedir cita para revisión semestral.",
                    priority = Priority.BAJA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "14 sep"
                ),

                Task(
                    id = 4,
                    title = "Preparar presentación Q3",
                    description = "Slides con métricas de producto para el board...",
                    priority = Priority.ALTA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "16 sep"
                ),

                Task(
                    id = 5,
                    title = "Renovar seguro del coche",
                    description = "Comparar precios con al menos 3 aseguradoras.",
                    priority = Priority.MEDIA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "18 sep"
                ),

                Task(
                    id = 6,
                    title = "Preparar práctica de Inteligencia Artificial",
                    description = "Terminar el modelo y preparar la documentación.",
                    priority = Priority.ALTA,
                    status = TaskStatus.PENDIENTE,
                    dueDate = "19 sep"
                ),

                Task(
                    id = 7,
                    title = "Entregar proyecto de Redes",
                    description = "Subir los archivos finales al repositorio.",
                    priority = Priority.MEDIA,
                    status = TaskStatus.COMPLETA,
                    dueDate = "10 sep"
                ),

                Task(
                    id = 8,
                    title = "Estudiar para Compiladores",
                    description = "Repasar análisis sintáctico y árboles.",
                    priority = Priority.BAJA,
                    status = TaskStatus.COMPLETA,
                    dueDate = "9 sep"
                )
            )
        )
    }

    // ─────────────────────────────────
    // Conteos
    // ─────────────────────────────────

    val allCount = tasks.size

    val pendingCount = tasks.count {
        it.status == TaskStatus.PENDIENTE
    }

    val completedCount = tasks.count {
        it.status == TaskStatus.COMPLETA
    }

    // ─────────────────────────────────
    // Filtrado
    // ─────────────────────────────────

    val filteredTasks = when (selectedFilter) {

        0 -> tasks

        1 -> tasks.filter {
            it.status == TaskStatus.PENDIENTE
        }

        else -> tasks.filter {
            it.status == TaskStatus.COMPLETA
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (showTaskForm || taskToDelete != null) {
                    Modifier.blur(18.dp)
                } else {
                    Modifier
                }
            )
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface.copy(
                            alpha = 0.75f
                        ),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {

        // ─────────────────────────────
        // Orbes decorativos
        // ─────────────────────────────

        Box(
            modifier = Modifier
                .size(190.dp)
                .offset(
                    x = 175.dp,
                    y = (-70).dp
                )
                .alpha(0.065f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(
                    x = (-75).dp,
                    y = 300.dp
                )
                .alpha(0.025f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        // ─────────────────────────────
        // Contenido
        // ─────────────────────────────

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(
                modifier = Modifier.height(55.dp)
            )

            // ─────────────────────────
            // Título
            // ─────────────────────────

            Text(
                text = "Todas mis tareas",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 22.sp,
                fontWeight =
                    androidx.compose.ui.text.font.FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ─────────────────────────
            // Filtros
            // ─────────────────────────

            TaskFilters(
                selectedFilter = selectedFilter,
                allCount = allCount,
                pendingCount = pendingCount,
                completedCount = completedCount,
                onFilterSelected = {
                    selectedFilter = it
                }
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // ─────────────────────────
            // Lista
            // ─────────────────────────

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    bottom = 110.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = filteredTasks,
                    key = {
                        it.id
                    }
                ) { task ->

                    TaskCard(
                        title = task.title,
                        description = task.description,
                        priority = task.priority,
                        dueDate = task.dueDate,
                        completed = task.status == TaskStatus.COMPLETA,

                        onClick = {
                            onTaskClick(task.id)
                        },

                        onToggleComplete = {
                            tasks = tasks.map {

                                if (it.id == task.id) {

                                    it.copy(
                                        status =
                                            if (
                                                it.status ==
                                                TaskStatus.COMPLETA
                                            ) {
                                                TaskStatus.PENDIENTE
                                            } else {
                                                TaskStatus.COMPLETA
                                            }
                                    )

                                } else {
                                    it
                                }
                            }
                        },

                        onEdit = {
                            taskToEdit = task
                            showTaskForm = true
                        },

                        onDelete = {
                            taskToDelete = task
                        }
                    )
                }

                if (filteredTasks.isEmpty()) {

                    item {
                        EmptyTasks()
                    }
                }
            }
        }

        // ─────────────────────────────
        // Bottom navigation
        // ─────────────────────────────

        GlassBottomNav(
            current = currentDestination,

            onNavigate = { destination ->

                currentDestination = destination

                when (destination) {

                    BottomNavDestination.HOME -> {
                        onNavigate(destination)
                    }

                    BottomNavDestination.ALL_TASKS -> {
                        onNavigate(destination)
                    }

                    BottomNavDestination.PROFILE -> {
                        onNavigate(destination)
                    }
                }
            },

            onCreateTask = {
                taskToEdit = null
                showTaskForm = true
            },

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                )
        )
    }

    if (showTaskForm) {
        TaskFormScreen(
            taskId = taskToEdit?.id,
            task = taskToEdit,
            onDismiss = {
                showTaskForm = false
                taskToEdit = null
            },
            onCreateTask = { _, _, _, _ ->
                showTaskForm = false
                taskToEdit = null
            }
        )
    }

    if (taskToDelete != null) {
        DeleteTaskScreen(
            taskTitle = taskToDelete!!.title,
            onDismiss = {
                taskToDelete = null
            },
            onConfirm = {
                taskToDelete = null
            }
        )
    }
}

// ═════════════════════════════════════
// FILTROS
// ═════════════════════════════════════

@Composable
private fun TaskFilters(
    selectedFilter: Int,
    allCount: Int,
    pendingCount: Int,
    completedCount: Int,
    onFilterSelected: (Int) -> Unit
) {

    GlassSurface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        backgroundColor = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.48f
        ),
        borderColor = MaterialTheme.colorScheme.outline.copy(
            alpha = 0.08f
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterItem(
                label = "Todas",
                count = allCount,
                selected = selectedFilter == 0,
                onClick = {
                    onFilterSelected(0)
                },
                modifier = Modifier.weight(1f)
            )

            FilterItem(
                label = "Pendientes",
                count = pendingCount,
                selected = selectedFilter == 1,
                onClick = {
                    onFilterSelected(1)
                },
                modifier = Modifier.weight(1f)
            )

            FilterItem(
                label = "Completadas",
                count = completedCount,
                selected = selectedFilter == 2,
                onClick = {
                    onFilterSelected(2)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ═════════════════════════════════════
// FILTRO INDIVIDUAL
// ═════════════════════════════════════

@Composable
private fun FilterItem(
    label: String,
    count: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val primary = MaterialTheme.colorScheme.primary

    GlassSurface(
        modifier = modifier
            .height(38.dp)
            .clickable(
                onClick = onClick
            ),
        shape = CircleShape,
        backgroundColor =
            if (selected) {
                primary.copy(alpha = 0.11f)
            } else {
                androidx.compose.ui.graphics.Color.Transparent
            },
        borderColor =
            if (selected) {
                primary.copy(alpha = 0.24f)
            } else {
                androidx.compose.ui.graphics.Color.Transparent
            },
        showBorder = selected
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = label,
                color =
                    if (selected) {
                        primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                fontSize = 10.sp,
                fontWeight =
                    if (selected) {
                        androidx.compose.ui.text.font.FontWeight.Medium
                    } else {
                        androidx.compose.ui.text.font.FontWeight.Normal
                    }
            )

            Spacer(
                modifier = Modifier.width(5.dp)
            )

            Box(
                modifier = Modifier
                    .background(
                        color =
                            if (selected) {
                                primary.copy(alpha = 0.10f)
                            } else {
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant
                                    .copy(alpha = 0.08f)
                            },
                        shape = CircleShape
                    )
                    .padding(
                        horizontal = 6.dp,
                        vertical = 2.dp
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = count.toString(),
                    color =
                        if (selected) {
                            primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                    fontSize = 9.sp
                )
            }
        }
    }
}

// ═════════════════════════════════════
// EMPTY STATE
// ═════════════════════════════════════

@Composable
private fun EmptyTasks() {

    GlassSurface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Todo en orden ✨",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight =
                    androidx.compose.ui.text.font.FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "No hay tareas en esta categoría.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
    }
}