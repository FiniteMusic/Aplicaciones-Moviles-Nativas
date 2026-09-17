package com.marcudlcv.dolist.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.ui.theme.GlassTokens
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun TaskCard(
    title: String,
    description: String?,
    priority: Priority,
    dueDate: String?,
    completed: Boolean,
    onClick: () -> Unit,
    onToggleComplete: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    val contentAlpha by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (completed) 0.50f else 1f,
        animationSpec = tween(
            durationMillis = 300
        ),
        label = "task_content_alpha"
    )

    GlassSurface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(
            GlassTokens.RadiusMedium
        ),
        backgroundColor = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.58f
        ),
        borderColor = MaterialTheme.colorScheme.outline.copy(
            alpha = 0.09f
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 14.dp,
                    end = 10.dp,
                    top = 14.dp,
                    bottom = 14.dp
                ),
            verticalAlignment = Alignment.Top
        ) {

            // ─────────────────────────────
            // Checkbox
            // ─────────────────────────────

            TaskCheckbox(
                checked = completed,
                onClick = onToggleComplete
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            // ─────────────────────────────
            // Contenido
            // ─────────────────────────────

            Column(
                modifier = Modifier
                    .weight(1f)
                    .alpha(contentAlpha),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                // ─────────────────────────
                // Título
                // ─────────────────────────

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (completed) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    },
                    maxLines = 1
                )

                // ─────────────────────────
                // Descripción
                // ─────────────────────────

                if (!description.isNullOrBlank()) {

                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        maxLines = 2
                    )
                }

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                // ─────────────────────────
                // Metadata
                // ─────────────────────────

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    PriorityChip(
                        priority = priority
                    )

                    if (!dueDate.isNullOrBlank()) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.65f
                                ),
                                modifier = Modifier.size(11.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(3.dp)
                            )

                            Text(
                                text = dueDate,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        }
                    }

                    // Estado

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(
                                    color = if (completed) {
                                        Color(0xFF5CF5A8)
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                            alpha = 0.40f
                                        )
                                    },
                                    shape = CircleShape
                                )
                        )

                        Spacer(
                            modifier = Modifier.width(3.dp)
                        )

                        Text(
                            text = if (completed) {
                                "Completa"
                            } else {
                                "Pendiente"
                            },
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            // ─────────────────────────────
            // Menú
            // ─────────────────────────────

            Box {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "Opciones",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.50f),
                    modifier = Modifier
                        .padding(start = 4.dp, top = 1.dp)
                        .size(18.dp)
                        .clickable {
                            showMenu = true
                        }
                )

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = {
                        showMenu = false
                    },
                    modifier = Modifier
                        .width(140.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.10f),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
                    tonalElevation = 0.dp,
                    shadowElevation = 6.dp
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Editar",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                        onClick = {
                            showMenu = false
                            onEdit()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.72f
                                )
                            )
                        },
                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 2.dp
                        )
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Eliminar",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                        onClick = {
                            showMenu = false
                            onDelete()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.error.copy(
                                    alpha = 0.88f
                                )
                            )
                        },
                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 2.dp
                        )
                    )
                }
            }
        }
    }
}


// ═════════════════════════════════════
// CHECKBOX ANIMADO
// ═════════════════════════════════════

@Composable
private fun TaskCheckbox(
    checked: Boolean,
    onClick: () -> Unit
) {

    val primaryColor = MaterialTheme.colorScheme.primary

    val backgroundColor by animateColorAsState(
        targetValue = if (checked) {
            primaryColor
        } else {
            Color.Transparent
        },
        animationSpec = tween(
            durationMillis = 250
        ),
        label = "checkbox_background"
    )

    val borderColor by animateColorAsState(
        targetValue = if (checked) {
            primaryColor
        } else {
            primaryColor.copy(alpha = 0.24f)
        },
        animationSpec = tween(
            durationMillis = 250
        ),
        label = "checkbox_border"
    )

    Box(
        modifier = Modifier
            .size(24.dp)
            .then(
                if (!checked) {
                    Modifier.border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = borderColor
                        ),
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(
            targetState = checked,
            transitionSpec = {
                (
                        fadeIn(
                            animationSpec = tween(180)
                        ) +
                                scaleIn(
                                    initialScale = 0.65f,
                                    animationSpec = tween(220)
                                )
                        ) togetherWith
                        (
                                fadeOut(
                                    animationSpec = tween(120)
                                ) +
                                        scaleOut(
                                            targetScale = 0.65f,
                                            animationSpec = tween(120)
                                        )
                                )
            },
            label = "checkbox_icon"
        ) { isChecked ->

            if (isChecked) {

                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Tarea completada",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            } else {

                Box(
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}