package com.marcudlcv.dolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.domain.model.Priority
import com.marcudlcv.dolist.ui.theme.PriorityHigh
import com.marcudlcv.dolist.ui.theme.PriorityLow
import com.marcudlcv.dolist.ui.theme.PriorityMedium

@Composable
fun PriorityChip(
    priority: Priority,
    modifier: Modifier = Modifier
) {
    val backgroundColor: androidx.compose.ui.graphics.Color
    val borderColor: androidx.compose.ui.graphics.Color
    val contentColor: androidx.compose.ui.graphics.Color

    when (priority) {
        Priority.ALTA -> {
            backgroundColor = PriorityHigh.copy(alpha = 0.15f)
            borderColor = PriorityHigh.copy(alpha = 0.28f)
            contentColor = PriorityHigh
        }

        Priority.MEDIA -> {
            backgroundColor = PriorityMedium.copy(alpha = 0.15f)
            borderColor = PriorityMedium.copy(alpha = 0.28f)
            contentColor = PriorityMedium
        }

        Priority.BAJA -> {
            backgroundColor = PriorityLow.copy(alpha = 0.15f)
            borderColor = PriorityLow.copy(alpha = 0.28f)
            contentColor = PriorityLow
        }
    }

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(50)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = contentColor,
                    shape = CircleShape
                )
        )

        Spacer(
            modifier = Modifier.size(6.dp)
        )

        Text(
            text = when (priority) {
                Priority.ALTA -> "Alta"
                Priority.MEDIA -> "Media"
                Priority.BAJA -> "Baja"
            },
            color = contentColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}