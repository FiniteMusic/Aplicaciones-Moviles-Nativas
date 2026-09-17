package com.marcudlcv.dolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.ui.theme.GlassTokens
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

enum class BottomNavDestination {
    HOME,
    ALL_TASKS,
    PROFILE
}


// ═════════════════════════════════════
// BOTTOM NAVIGATION
// ═════════════════════════════════════

@Composable
fun GlassBottomNav(
    current: BottomNavDestination,
    onNavigate: (BottomNavDestination) -> Unit,
    onCreateTask: () -> Unit,
    modifier: Modifier = Modifier
) {

    GlassSurface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            GlassTokens.RadiusLarge
        ),
        backgroundColor = MaterialTheme.colorScheme.surface.copy(
            alpha = 0.78f
        ),
        borderColor = MaterialTheme.colorScheme.outline.copy(
            alpha = 0.12f
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ─────────────────────────
            // Inicio
            // ─────────────────────────

            NavItem(
                icon = Icons.Filled.Home,
                label = "Inicio",
                selected = current == BottomNavDestination.HOME,
                onClick = {
                    onNavigate(BottomNavDestination.HOME)
                }
            )

            // ─────────────────────────
            // Mis tareas
            // ─────────────────────────

            NavItem(
                icon = Icons.Filled.CheckCircle,
                label = "Mis tareas",
                selected = current == BottomNavDestination.ALL_TASKS,
                onClick = {
                    onNavigate(BottomNavDestination.ALL_TASKS)
                }
            )

            // ─────────────────────────
            // Crear tarea
            // ─────────────────────────

            CreateTaskButton(
                onClick = onCreateTask
            )

            // ─────────────────────────
            // Perfil
            // ─────────────────────────

            NavItem(
                icon = Icons.Filled.Person,
                label = "Perfil",
                selected = current == BottomNavDestination.PROFILE,
                onClick = {
                    onNavigate(BottomNavDestination.PROFILE)
                }
            )
        }
    }
}


// ═════════════════════════════════════
// NAV ITEM
// ═════════════════════════════════════

@Composable
private fun NavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val activeColor = MaterialTheme.colorScheme.primary

    val inactiveColor =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(
            alpha = 0.72f
        )

    val animatedColor by animateColorAsState(
        targetValue = if (selected) {
            activeColor
        } else {
            inactiveColor
        },
        animationSpec = tween(
            durationMillis = 220
        ),
        label = "nav_color"
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (selected) {
            1.06f
        } else {
            1f
        },
        animationSpec = tween(
            durationMillis = 220
        ),
        label = "nav_scale"
    )

    Column(
        modifier = Modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 11.dp,
                vertical = 5.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(22.dp),
            tint = animatedColor
        )

        Text(
            text = label,
            color = animatedColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

// ═════════════════════════════════════
// CREATE TASK BUTTON
// ═════════════════════════════════════

@Composable
private fun CreateTaskButton(
    onClick: () -> Unit
) {

    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    var pressed by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(false)
    }

    val animatedScale by animateFloatAsState(
        targetValue = if (pressed) {
            0.92f
        } else {
            1f
        },
        animationSpec = tween(
            durationMillis = 120
        ),
        label = "fab_press"
    )

    Box(
        modifier = Modifier
            .size(58.dp)
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                ambientColor = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.22f
                ),
                spotColor = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.28f
                )
            )
            .clip(CircleShape)
            .background(gradient)
            .clickable {

                pressed = true
                onClick()

                pressed = false
            },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Crear tarea",
            tint = Color.White,
            modifier = Modifier.size(29.dp)
        )
    }
}