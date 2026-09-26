package com.marcudlcv.f1uigaragecompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.f1uigaragecompose.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FeedbackScreen() {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showPitDialog by remember {
        mutableStateOf(false)
    }

    var raceProgress by remember {
        mutableFloatStateOf(0f)
    }

    var progressRunning by remember {
        mutableStateOf(false)
    }

    var circularLoading by remember {
        mutableStateOf(false)
    }

    var validationCode by remember {
        mutableStateOf("")
    }

    var validationError by remember {
        mutableStateOf<String?>(null)
    }

    var status by remember {
        mutableStateOf("Sistema de retroalimentación listo.")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GarageBackground)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 100.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // =================================
            // ENCABEZADO
            // =================================

            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(GarageHeader)
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {

                        Text(
                            text = "ASTON MARTIN · SECCIÓN 05",
                            color = AstonMartin,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text = "Retroalimentación",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Comunica resultados, advertencias, errores y procesos mediante diferentes mecanismos de feedback.",
                            color = Color(0xFFD5D8DF),
                            fontSize = 14.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .background(AstonMartin)
                    )
                }
            }

            // =================================
            // 1. TOAST
            // =================================

            item {

                FeedbackDocumentation(
                    title = "1. Toast",
                    description = "Toast muestra un mensaje temporal del sistema. Es útil para confirmar acciones breves que no requieren interacción adicional."
                )

                Button(
                    onClick = {

                        Toast.makeText(
                            context,
                            "Telemetría enviada correctamente",
                            Toast.LENGTH_SHORT
                        ).show()

                        status =
                            "La telemetría fue enviada."
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {
                    Text("Mostrar Toast")
                }
            }

            // =================================
            // 2. SNACKBAR
            // =================================

            item {

                FeedbackDocumentation(
                    title = "2. Snackbar",
                    description = "Snackbar muestra información temporal dentro de la aplicación y puede incorporar una acción, como deshacer una operación."
                )

                Button(
                    onClick = {

                        coroutineScope.launch {

                            status =
                                "Configuración eliminada."

                            val result =
                                snackbarHostState.showSnackbar(
                                    message = "Configuración eliminada",
                                    actionLabel = "Deshacer",
                                    duration = SnackbarDuration.Short
                                )

                            if (
                                result ==
                                SnackbarResult.ActionPerformed
                            ) {

                                status =
                                    "Eliminación cancelada."
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {
                    Text("Mostrar Snackbar")
                }
            }

            // =================================
            // 3. ALERTDIALOG
            // =================================

            item {

                FeedbackDocumentation(
                    title = "3. AlertDialog",
                    description = "AlertDialog solicita confirmación antes de ejecutar una acción importante y puede presentar opciones para aceptar o cancelar."
                )

                Button(
                    onClick = {
                        showPitDialog = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {
                    Text("Solicitar parada en pits")
                }
            }

            // =================================
            // 4. PROGRESO DETERMINADO
            // =================================

            item {

                FeedbackDocumentation(
                    title = "4. LinearProgressIndicator",
                    description = "Un indicador determinado representa el avance conocido de una tarea. El valor se expresa en Compose entre 0 y 1."
                )

                Text(
                    text = "Simulación: ${(raceProgress * 100).toInt()}%",
                    color = GarageTextPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                LinearProgressIndicator(
                    progress = {
                        raceProgress
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = AstonMartin
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Button(
                    onClick = {

                        if (!progressRunning) {

                            progressRunning = true
                            raceProgress = 0f
                            status = "Simulación en progreso..."

                            coroutineScope.launch {

                                for (step in 1..100) {

                                    delay(30)

                                    raceProgress =
                                        step / 100f
                                }

                                progressRunning = false

                                status =
                                    "Simulación completada."
                            }
                        }
                    },
                    enabled = !progressRunning,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {

                    Text(
                        if (progressRunning) {
                            "Simulando..."
                        } else {
                            "Iniciar simulación"
                        }
                    )
                }
            }

            // =================================
            // 5. PROGRESO INDETERMINADO
            // =================================

            item {

                FeedbackDocumentation(
                    title = "5. CircularProgressIndicator",
                    description = "El progreso indeterminado se utiliza cuando una operación está activa pero no se conoce con precisión cuánto falta para terminar."
                )

                Button(
                    onClick = {

                        if (!circularLoading) {

                            circularLoading = true

                            status =
                                "Sincronizando telemetría..."

                            coroutineScope.launch {

                                delay(2500)

                                circularLoading = false

                                status =
                                    "Telemetría sincronizada."
                            }
                        }
                    },
                    enabled = !circularLoading,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {

                    if (circularLoading) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Text("Sincronizando...")

                    } else {

                        Text("Sincronizar telemetría")
                    }
                }
            }

            // =================================
            // 6. ERROR DE VALIDACIÓN
            // =================================

            item {

                FeedbackDocumentation(
                    title = "6. Error de validación",
                    description = "OutlinedTextField puede representar visualmente un error mediante isError y supportingText para explicar qué debe corregir el usuario."
                )

                OutlinedTextField(
                    value = validationCode,
                    onValueChange = {

                        validationCode = it

                        validationError = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Código de pits")
                    },
                    placeholder = {
                        Text("Escribe BOX")
                    },
                    singleLine = true,
                    isError = validationError != null,
                    supportingText = {

                        validationError?.let {
                            Text(it)
                        }
                    }
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Button(
                    onClick = {

                        if (
                            validationCode.trim()
                                .equals(
                                    "BOX",
                                    ignoreCase = true
                                )
                        ) {

                            validationError = null

                            status =
                                "Código BOX validado correctamente."

                        } else {

                            validationError =
                                "Código incorrecto. Escribe BOX."

                            status =
                                "Error en el código de pits."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AstonMartinAccent
                    )
                ) {
                    Text("Validar código")
                }
            }

            // =================================
            // 7. TARJETA DE ESTADO
            // =================================

            item {

                FeedbackDocumentation(
                    title = "7. Estado visual",
                    description = "La interfaz puede utilizar estado observable para comunicar inmediatamente el resultado de las interacciones realizadas en otros componentes."
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    color = GarageHeader
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "ESTADO DEL SISTEMA",
                            color = AstonMartin,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text = status,
                            color = Color.White,
                            fontSize = 14.sp
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text =
                                "Progreso: ${(raceProgress * 100).toInt()}%",
                            color = Color(0xFFD5D8DF),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(
                    androidx.compose.ui.Alignment.BottomCenter
                )
                .padding(16.dp)
        )
    }

    // =================================
    // DIALOG
    // =================================

    if (showPitDialog) {

        AlertDialog(
            onDismissRequest = {
                showPitDialog = false
            },
            title = {
                Text("Confirmar parada")
            },
            text = {
                Text(
                    "¿Deseas llamar al piloto a boxes en la siguiente vuelta?"
                )
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        showPitDialog = false

                        status =
                            "Parada en pits confirmada."
                    }
                ) {

                    Text(
                        text = "CONFIRMAR",
                        color = AstonMartin
                    )
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {

                        showPitDialog = false

                        status =
                            "Parada en pits cancelada."
                    }
                ) {

                    Text("CANCELAR")
                }
            }
        )
    }
}


@Composable
private fun FeedbackDocumentation(
    title: String,
    description: String
) {

    Column {

        Text(
            text = title,
            color = GarageTextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = description,
            color = GarageTextSecondary,
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}