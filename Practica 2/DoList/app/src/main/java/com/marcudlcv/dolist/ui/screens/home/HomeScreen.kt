package com.marcudlcv.dolist.ui.screens.home

import androidx.compose.animation.core.animateFloatAsState
import java.util.Calendar
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.domain.model.Task
import com.marcudlcv.dolist.domain.model.TaskStatus
import com.marcudlcv.dolist.ui.components.BottomNavDestination
import com.marcudlcv.dolist.ui.components.GlassBottomNav
import com.marcudlcv.dolist.ui.components.GlassSurface
import com.marcudlcv.dolist.ui.components.TaskCard
import com.marcudlcv.dolist.ui.screens.tasks.DeleteTaskScreen
import com.marcudlcv.dolist.ui.screens.tasks.TaskFormScreen
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun HomeScreen(
    userName: String = "Usuario",
    tasks: List<Task> = emptyList(),
    onTaskClick: (Int) -> Unit = {},
    onCreateTask: () -> Unit = {},
    onCreateTaskWithData: (
        title: String,
        description: String,
        priority: Priority,
        dueDate: String?
    ) -> Unit = { _, _, _, _ -> },
    onUpdateTask: (
        id: Int,
        title: String,
        description: String,
        priority: Priority,
        status: TaskStatus,
        dueDate: String?
    ) -> Unit = { _, _, _, _, _, _ -> },
    onEditTask: (Int) -> Unit = {},
    onDeleteTask: (Int) -> Unit = {},
    onNavigate: (BottomNavDestination) -> Unit = {}
) {

    var currentDestination by remember {
        mutableStateOf(BottomNavDestination.HOME)
    }

    var taskToDelete by remember {
        mutableStateOf<Task?>(null)
    }

    var showTaskForm by remember {
        mutableStateOf(false)
    }

    var taskToEdit by remember {
        mutableStateOf<Task?>(null)
    }

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val greeting = when (currentHour) {
        in 5..11 -> "Buenos días 👋"
        in 12..18 -> "Buenas tardes 👋"
        else -> "Buenas noches 👋"
    }

    val mexicoTimeZone = TimeZone.getTimeZone("America/Mexico_City")

    val today = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.US
    ).apply {
        timeZone = mexicoTimeZone
    }.format(Calendar.getInstance(mexicoTimeZone).time)

    fun getTaskDate(dueDate: String?): String? {
        return dueDate?.takeIf { it.length >= 10 }?.substring(0, 10)
    }

    fun formatTaskDate(dueDate: String?): String? {
        val dateOnly = getTaskDate(dueDate) ?: return dueDate

        return try {
            val parser = SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.US
            ).apply {
                timeZone = TimeZone.getTimeZone("UTC")
                isLenient = false
            }

            val formatter = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.US
            ).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }

            parser.parse(dateOnly)?.let {
                formatter.format(it)
            } ?: dueDate
        } catch (e: Exception) {
            dueDate
        }
    }

    val todayTasks = tasks.filter {
        getTaskDate(it.dueDate) == today
    }

    val upcomingTasks = tasks.filter {
        val taskDate = getTaskDate(it.dueDate)

        taskDate != null && taskDate > today
    }

    val completedToday = todayTasks.count {
        it.status == TaskStatus.COMPLETA
    }

    val pendingToday = todayTasks.count {
        it.status == TaskStatus.PENDIENTE
    }

    val progress = if (todayTasks.isNotEmpty()) {
        completedToday.toFloat() / todayTasks.size.toFloat()
    } else {
        0f
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
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {

        // ─────────────────────────────
        // ORBES DECORATIVOS
        // ─────────────────────────────

        Box(
            modifier = Modifier
                .size(190.dp)
                .offset(
                    x = 175.dp,
                    y = (-70).dp
                )
                .alpha(0.08f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(
                    x = (-80).dp,
                    y = 210.dp
                )
                .alpha(0.035f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        // ─────────────────────────────
        // CONTENIDO PRINCIPAL
        // ─────────────────────────────

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            // ─────────────────────────
            // ENCABEZADO
            // ─────────────────────────

            Spacer(
                modifier = Modifier.height(55.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = greeting,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                GlassSurface(
                    modifier = Modifier
                        .size(46.dp)
                        .clickable {
                            onNavigate(BottomNavDestination.PROFILE)
                        },
                    shape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.08f
                    ),
                    borderColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.16f
                    )
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Perfil",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ─────────────────────────
            // PROGRESO DE HOY
            // ─────────────────────────

            ProgressCard(
                completed = completedToday,
                total = todayTasks.size,
                pending = pendingToday,
                progress = progress
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ─────────────────────────
            // LISTA DE TAREAS
            // ─────────────────────────

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    bottom = 110.dp
                ),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                // ─────────────────────
                // HOY
                // ─────────────────────

                item {
                    TaskSectionHeader(
                        title = "Hoy",
                        count = todayTasks.size
                    )
                }

                items(
                    items = todayTasks,
                    key = { it.id }
                ) { task ->

                    TaskCard(
                        title = task.title,
                        description = task.description,
                        priority = task.priority,
                        dueDate = formatTaskDate(task.dueDate),
                        completed = task.status == TaskStatus.COMPLETA,

                        onClick = {
                            onTaskClick(task.id)
                        },

                        onToggleComplete = {
                            onUpdateTask(
                                task.id,
                                task.title,
                                task.description ?: "",
                                task.priority,
                                if (task.status == TaskStatus.COMPLETA) {
                                    TaskStatus.PENDIENTE
                                } else {
                                    TaskStatus.COMPLETA
                                },
                                getTaskDate(task.dueDate)
                            )
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

                // ─────────────────────
                // PRÓXIMAS
                // ─────────────────────

                item {

                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    TaskSectionHeader(
                        title = "Próximas",
                        count = upcomingTasks.size
                    )
                }

                items(
                    items = upcomingTasks,
                    key = { it.id }
                ) { task ->

                    TaskCard(
                        title = task.title,
                        description = task.description,
                        priority = task.priority,
                        dueDate = formatTaskDate(task.dueDate),
                        completed = task.status == TaskStatus.COMPLETA,

                        onClick = {
                            onTaskClick(task.id)
                        },

                        onToggleComplete = {
                            onUpdateTask(
                                task.id,
                                task.title,
                                task.description ?: "",
                                task.priority,
                                if (task.status == TaskStatus.COMPLETA) {
                                    TaskStatus.PENDIENTE
                                } else {
                                    TaskStatus.COMPLETA
                                },
                                getTaskDate(task.dueDate)
                            )
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

                // ─────────────────────
                // ESTADO VACÍO
                // ─────────────────────

                if (todayTasks.isEmpty() && upcomingTasks.isEmpty()) {
                    item {
                        EmptyTasks()
                    }
                }
            }
        }

        // ─────────────────────────────
        // BARRA INFERIOR
        // ─────────────────────────────

        GlassBottomNav(
            current = currentDestination,

            onNavigate = { destination ->
                currentDestination = destination
                onNavigate(destination)
            },

            onCreateTask = {
                taskToEdit = null
                showTaskForm = true
            },

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        )
    }

    // ─────────────────────────────
    // FORMULARIO DE TAREA
    // ─────────────────────────────

    if (showTaskForm) {

        TaskFormScreen(
            taskId = taskToEdit?.id,
            task = taskToEdit,

            onDismiss = {
                showTaskForm = false
                taskToEdit = null
            },

            onCreateTask = { title, description, priority, dueDate ->

                onCreateTaskWithData(
                    title,
                    description,
                    priority,
                    dueDate
                )

                showTaskForm = false
                taskToEdit = null
            }
        )
    }

    // ─────────────────────────────
    // ELIMINAR TAREA
    // ─────────────────────────────

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
// PROGRESS CARD
// ═════════════════════════════════════

@Composable
private fun ProgressCard(
    completed: Int,
    total: Int,
    pending: Int,
    progress: Float
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 700
        ),
        label = "progress_animation"
    )

    val animatedPercentage =
        (animatedProgress * 100).toInt()

    val primary = MaterialTheme.colorScheme.primary

    GlassSurface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = primary.copy(alpha = 0.10f),
        borderColor = primary.copy(alpha = 0.20f)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Progreso de hoy",
                    color = primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(1.dp)
                )

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {

                    Text(
                        text = completed.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "/$total",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(
                            start = 3.dp,
                            bottom = 3.dp
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(
                                alpha = 0.45f
                            ),
                            shape = CircleShape
                        )
                        .padding(
                            horizontal = 11.dp,
                            vertical = 5.dp
                        )
                ) {

                    Text(
                        text = "$pending pendientes",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }

            // Indicador circular

            Box(
                modifier = Modifier.size(56.dp),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.fillMaxSize(),
                    color = primary,
                    trackColor = primary.copy(alpha = 0.12f),
                    strokeWidth = 4.dp
                )

                Text(
                    text = "$animatedPercentage%",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// ═════════════════════════════════════
// SECTION HEADER
// ═════════════════════════════════════

@Composable
private fun TaskSectionHeader(
    title: String,
    count: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.size(8.dp)
        )

        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    ),
                    shape = CircleShape
                )
                .padding(
                    horizontal = 7.dp,
                    vertical = 3.dp
                )
        ) {

            Text(
                text = count.toString(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
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
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "No tienes tareas para mostrar aquí.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}