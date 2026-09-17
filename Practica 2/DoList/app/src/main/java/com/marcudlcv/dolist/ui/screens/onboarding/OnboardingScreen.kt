package com.marcudlcv.dolist.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.ui.components.AuthBackground
import com.marcudlcv.dolist.ui.components.DoListLogo
import com.marcudlcv.dolist.ui.components.GlassButton

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    AuthBackground {

        // ─────────────────────────────────────────
        // Contenido principal
        // ─────────────────────────────────────────

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 28.dp,
                    vertical = 32.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // ─────────────────────────────────────────
            // Logo principal
            // ─────────────────────────────────────────

            Box(
                modifier = Modifier
                    .padding(28.dp),
                contentAlignment = Alignment.Center
            ) {
                DoListLogo(
                    modifier = Modifier.width(200.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // ─────────────────────────────────────────
            // Mensaje principal
            // ─────────────────────────────────────────

            Text(
                text = "Menos caos, más tú.",
                style = MaterialTheme.typography.headlineSmall,
                color = colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Organiza tus tareas, alcanza tus objetivos y mantén todo bajo control.",
                style = MaterialTheme.typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            // ─────────────────────────────────────────
            // Botón comenzar
            // ─────────────────────────────────────────

            GlassButton(
                text = "Comenzar",
                onClick = onGetStarted,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // ─────────────────────────────────────────
        // Pequeño texto inferior
        // ─────────────────────────────────────────

        Text(
            text = "Tu productividad, a tu manera.",
            style = MaterialTheme.typography.labelMedium,
            color = colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
        )
    }
}