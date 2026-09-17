package com.marcudlcv.dolist.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.domain.model.Task
import com.marcudlcv.dolist.ui.components.GlassTextField
import java.time.Instant
import java.time.ZoneId
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    taskId: Int? = null,
    task: Task? = null,
    onDismiss: () -> Unit = {},
    onCreateTask: (
        title: String,
        description: String,
        priority: Priority,
        dueDate: String?
    ) -> Unit = { _, _, _, _ -> }
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedPriority by remember {
        mutableStateOf(Priority.MEDIA)
    }

    var selectedDate by remember {
        mutableStateOf<Long?>(null)
    }

    var dateText by remember {
        mutableStateOf("")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val isEditing = taskId != null || task != null

    // ─────────────────────────────────────────────
    // CARGAR DATOS DE LA TAREA AL EDITAR
    // ─────────────────────────────────────────────

    LaunchedEffect(task?.id) {
        if (task != null) {

            title = task.title

            description = task.description.orEmpty()

            selectedPriority = task.priority

            if (!task.dueDate.isNullOrBlank()) {

                dateText = task.dueDate

                try {
                    val parser = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.US
                    )

                    parser.isLenient = false

                    selectedDate = parser
                        .parse(task.dueDate)
                        ?.time

                } catch (_: Exception) {
                    selectedDate = null
                }
            }
        }
    }

    // ─────────────────────────────────────────────
    // FECHA MOSTRADA AL USUARIO
    // ─────────────────────────────────────────────

    val formattedDate = selectedDate?.let {
        SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date(it))
    }

    val displayedDate = formattedDate ?: dateText

    // ─────────────────────────────────────────────
    // BOTTOM SHEET
    // ─────────────────────────────────────────────

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxHeight(0.64f),
        containerColor = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.88f
        ),
        scrimColor = Color.Black.copy(alpha = 0.32f),
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        tonalElevation = 0.dp,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 4.dp
                    )
                    .size(
                        width = 34.dp,
                        height = 4.dp
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.28f
                        ),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.035f
                    )
                )
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    bottom = 6.dp
                )
        ) {

            // ─────────────────────────────────────────────
            // HEADER
            // ─────────────────────────────────────────────

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = if (isEditing) {
                        "Editar tarea"
                    } else {
                        "Nueva tarea"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Cerrar",
                        tint = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.48f
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // ─────────────────────────────────────────────
            // TÍTULO
            // ─────────────────────────────────────────────

            GlassTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                label = "Título",
                placeholder = "Título de la tarea",
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ─────────────────────────────────────────────
            // DESCRIPCIÓN
            // ─────────────────────────────────────────────

            GlassTextField(
                value = description,
                onValueChange = {
                    description = it
                },
                label = "Descripción",
                singleLine = false,
                modifier = Modifier.height(78.dp)
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // ─────────────────────────────────────────────
            // PRIORIDAD
            // ─────────────────────────────────────────────

            Text(
                text = "Prioridad",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.55f
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                PriorityOption(
                    text = "Alta",
                    priority = Priority.ALTA,
                    selected = selectedPriority == Priority.ALTA,
                    onClick = {
                        selectedPriority = Priority.ALTA
                    },
                    modifier = Modifier.weight(1f)
                )

                PriorityOption(
                    text = "Media",
                    priority = Priority.MEDIA,
                    selected = selectedPriority == Priority.MEDIA,
                    onClick = {
                        selectedPriority = Priority.MEDIA
                    },
                    modifier = Modifier.weight(1f)
                )

                PriorityOption(
                    text = "Baja",
                    priority = Priority.BAJA,
                    selected = selectedPriority == Priority.BAJA,
                    onClick = {
                        selectedPriority = Priority.BAJA
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // ─────────────────────────────────────────────
            // FECHA LÍMITE
            // ─────────────────────────────────────────────

            Text(
                text = "Fecha límite",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.55f
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            GlassTextField(
                value = displayedDate,
                onValueChange = {},
                label = "Fecha",
                placeholder = "Seleccionar fecha",
                singleLine = true,
                enabled = false,
                trailingIcon = Icons.Filled.CalendarToday,
                onTrailingIconClick = {
                    showDatePicker = true
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // ─────────────────────────────────────────────
            // BOTONES
            // ─────────────────────────────────────────────

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // CANCELAR

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme
                            .onSurface
                            .copy(alpha = 0.07f),
                        contentColor = MaterialTheme.colorScheme
                            .onSurface
                            .copy(alpha = 0.65f)
                    )
                ) {
                    Text(
                        text = "Cancelar",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // CREAR / GUARDAR CAMBIOS

                Button(
                    onClick = {

                        // Convertimos la fecha seleccionada
                        // al formato que espera el backend:
                        // YYYY-MM-DD
                        val formattedDueDate = selectedDate?.let { millis ->

                            Instant
                                .ofEpochMilli(millis)
                                .atZone(
                                    ZoneId.of(
                                        "America/Mexico_City"
                                    )
                                )
                                .toLocalDate()
                                .toString()
                        }

                        onCreateTask(
                            title.trim(),
                            description.trim(),
                            selectedPriority,
                            formattedDueDate
                        )
                    },
                    enabled = title.isNotBlank(),
                    modifier = Modifier
                        .weight(1.35f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor =
                            MaterialTheme.colorScheme
                                .onSurface
                                .copy(alpha = 0.08f)
                    ),
                    contentPadding = androidx.compose.foundation.layout
                        .PaddingValues(0.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(
                                brush = if (title.isNotBlank()) {

                                    Brush.linearGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.secondary
                                        )
                                    )

                                } else {

                                    Brush.linearGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme
                                                .onSurface
                                                .copy(alpha = 0.08f),
                                            MaterialTheme.colorScheme
                                                .onSurface
                                                .copy(alpha = 0.08f)
                                        )
                                    )
                                },
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = if (isEditing) {
                                "Guardar cambios"
                            } else {
                                "Crear tarea"
                            },
                            color = if (title.isNotBlank()) {
                                Color.White
                            } else {
                                MaterialTheme.colorScheme
                                    .onSurface
                                    .copy(alpha = 0.35f)
                            },
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────
    // DATE PICKER
    // ─────────────────────────────────────────────────────

    if (showDatePicker) {

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate
        )

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        selectedDate =
                            datePickerState.selectedDateMillis

                        dateText = selectedDate?.let { millis ->

                            Instant
                                .ofEpochMilli(millis)
                                .atZone(
                                    ZoneId.of(
                                        "America/Mexico_City"
                                    )
                                )
                                .toLocalDate()
                                .let { date ->

                                    "${date.dayOfMonth.toString().padStart(2, '0')}/" +
                                            "${date.monthValue.toString().padStart(2, '0')}/" +
                                            date.year
                                }

                        } ?: ""

                        showDatePicker = false
                    }
                ) {
                    Text(
                        text = "Aceptar",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }
}

// ═════════════════════════════════════════════════════
// OPCIÓN DE PRIORIDAD
// ═════════════════════════════════════════════════════

@Composable
private fun PriorityOption(
    text: String,
    priority: Priority,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val priorityColor = when (priority) {
        Priority.ALTA -> Color(0xFFFF6B8A)
        Priority.MEDIA -> Color(0xFFFFB347)
        Priority.BAJA -> Color(0xFF5CF5A8)
    }

    Box(
        modifier = modifier
            .height(38.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(
                color = if (selected) {

                    priorityColor.copy(alpha = 0.12f)

                } else {

                    MaterialTheme.colorScheme
                        .onSurface
                        .copy(alpha = 0.055f)
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            },
            color = if (selected) {
                priorityColor
            } else {
                MaterialTheme.colorScheme
                    .onSurface
                    .copy(alpha = 0.45f)
            }
        )
    }
}