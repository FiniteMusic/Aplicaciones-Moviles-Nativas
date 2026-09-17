package com.marcudlcv.dolist.ui.screens.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.ui.components.GlassSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTaskScreen(
    taskTitle: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        containerColor = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.90f
        ),
        scrimColor = Color.Black.copy(alpha = 0.32f),
        dragHandle = {
            GlassSurface(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(
                        width = 34.dp,
                        height = 4.dp
                    ),
                shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.28f
                ),
                showBorder = false
            ) {}
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // ─────────────────────────────────
            // ICONO DE ELIMINAR
            // ─────────────────────────────────

            GlassSurface(
                modifier = Modifier.size(54.dp),
                shape = RoundedCornerShape(18.dp),
                backgroundColor = MaterialTheme.colorScheme.error.copy(
                    alpha = 0.08f
                ),
                borderColor = MaterialTheme.colorScheme.error.copy(
                    alpha = 0.16f
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // ─────────────────────────────────
            // TITULO
            // ─────────────────────────────────

            Text(
                text = "¿Eliminar tarea?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // ─────────────────────────────────
            // DESCRIPCIÓN
            // ─────────────────────────────────

            Text(
                text = "Esta tarea se eliminará permanentemente.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "\"$taskTitle\"",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ─────────────────────────────────
            // BOTONES
            // ─────────────────────────────────

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // CANCELAR

                GlassSurface(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clickable {
                            onDismiss()
                        },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.045f
                    ),
                    borderColor = MaterialTheme.colorScheme.outline.copy(
                        alpha = 0.10f
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cancelar",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // ELIMINAR

                GlassSurface(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clickable {
                            onConfirm()
                        },
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = MaterialTheme.colorScheme.error.copy(
                        alpha = 0.88f
                    ),
                    borderColor = MaterialTheme.colorScheme.error.copy(
                        alpha = 0.45f
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Eliminar",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}