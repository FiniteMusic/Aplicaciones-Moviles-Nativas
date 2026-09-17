package com.marcudlcv.dolist.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.ui.components.GlassTextField
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    onDismiss: () -> Unit = {},
    onChangePassword: () -> Unit = {}
) {
    var currentPassword by remember {
        mutableStateOf("")
    }

    var newPassword by remember {
        mutableStateOf("")
    }

    var currentPasswordVisible by remember {
        mutableStateOf(false)
    }

    var newPasswordVisible by remember {
        mutableStateOf(false)
    }

    val canSubmit =
        currentPassword.isNotBlank() &&
                newPassword.isNotBlank()

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Cambiar contraseña",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "Actualiza la contraseña de tu cuenta",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    )
                }

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
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Contraseña actual",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.55f
                )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            GlassTextField(
                value = currentPassword,
                onValueChange = {
                    currentPassword = it
                },
                label = "Contraseña actual",
                placeholder = "Ingresa tu contraseña actual",
                singleLine = true,
                trailingIcon = if (currentPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                },
                onTrailingIconClick = {
                    currentPasswordVisible =
                        !currentPasswordVisible
                },
                visualTransformation =
                    if (currentPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = "Nueva contraseña",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.55f
                )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            GlassTextField(
                value = newPassword,
                onValueChange = {
                    newPassword = it
                },
                label = "Nueva contraseña",
                placeholder = "Ingresa tu nueva contraseña",
                singleLine = true,
                trailingIcon = if (newPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                },
                onTrailingIconClick = {
                    newPasswordVisible =
                        !newPasswordVisible
                },
                visualTransformation =
                    if (newPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.07f
                            ),
                        contentColor =
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.65f
                            )
                    )
                ) {
                    Text(
                        text = "Cancelar",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Button(
                    onClick = onChangePassword,
                    enabled = canSubmit,
                    modifier = Modifier
                        .weight(1.35f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor =
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.08f
                            )
                    ),
                    contentPadding =
                        androidx.compose.foundation.layout.PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(
                                brush = if (canSubmit) {
                                    Brush.linearGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.secondary
                                        )
                                    )
                                } else {
                                    Brush.linearGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            ),
                                            MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.08f
                                            )
                                        )
                                    )
                                },
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cambiar contraseña",
                            color = if (canSubmit) {
                                Color.White
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.35f
                                )
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}