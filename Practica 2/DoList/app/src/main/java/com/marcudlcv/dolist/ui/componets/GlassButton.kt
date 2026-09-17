package com.marcudlcv.dolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.ui.theme.GlassTokens

enum class GlassButtonStyle {
    PRIMARY,
    SECONDARY
}

@Composable
fun GlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: GlassButtonStyle = GlassButtonStyle.PRIMARY,
    enabled: Boolean = true,
    icon: ImageVector? = null
) {
    val shape = RoundedCornerShape(GlassTokens.RadiusLarge)

    val backgroundModifier = when (style) {
        GlassButtonStyle.PRIMARY -> {
            Modifier.background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                ),
                shape = shape
            )
        }

        GlassButtonStyle.SECONDARY -> {
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = shape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                    shape = shape
                )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .then(backgroundModifier)
            .alpha(if (enabled) 1f else 0.45f)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
                tint = if (style == GlassButtonStyle.PRIMARY) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }

        if (icon != null) {
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.size(8.dp)
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (style == GlassButtonStyle.PRIMARY) {
                Color.White
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}