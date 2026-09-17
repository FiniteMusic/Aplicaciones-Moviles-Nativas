package com.marcudlcv.dolist.ui.screens.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marcudlcv.dolist.ui.components.AuthBackground
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marcudlcv.dolist.ui.components.DoListLogo
import com.marcudlcv.dolist.ui.components.GlassButton
import com.marcudlcv.dolist.ui.components.GlassSurface
import com.marcudlcv.dolist.ui.components.GlassTextField
import com.marcudlcv.dolist.ui.theme.GlassTokens
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginScreen(
    onLogin: (email: String, password: String) -> Unit,
    onRegister: () -> Unit,
    errorMessage: String? = null,
    isLoading: Boolean = false,
){
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val colorScheme = MaterialTheme.colorScheme

    AuthBackground {

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

            // ─────────────────────────────────────
            // Logo
            // ─────────────────────────────────────

            DoListLogo(
                modifier = Modifier.width(200.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // ─────────────────────────────────────
            // Encabezado
            // ─────────────────────────────────────

            Text(
                text = "Bienvenido de nuevo",
                style = MaterialTheme.typography.headlineMedium,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Inicia sesión para continuar con DoList",
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // ─────────────────────────────────────
            // Formulario
            // ─────────────────────────────────────

            GlassSurface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    GlassTokens.RadiusLarge
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    // Correo
                    GlassTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = "Correo electrónico",
                        placeholder = "correo@ejemplo.com"
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // Contraseña
                    GlassTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = "Contraseña",
                        placeholder = "Ingresa tu contraseña",
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = if (passwordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        onTrailingIconClick = {
                            passwordVisible = !passwordVisible
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodySmall,
                            color = colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                    }
                    // Recuperar contraseña
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // Iniciar sesión
                    GlassButton(
                        text = if (isLoading) "Iniciando sesión..." else "Iniciar sesión",
                        onClick = {
                            if (!isLoading) {
                                onLogin(email, password)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ─────────────────────────────────────
            // Registro
            // ─────────────────────────────────────

            Text(
                text = "¿No tienes una cuenta?",
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Crear una cuenta",
                style = MaterialTheme.typography.labelLarge,
                color = colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable {
                        onRegister()
                    }
            )
        }
    }
}