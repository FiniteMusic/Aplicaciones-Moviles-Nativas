package com.marcudlcv.f1uigaragecompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import com.marcudlcv.f1uigaragecompose.ui.theme.GarageBackground
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageHeader
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageSurface
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageTextPrimary
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageTextSecondary
import com.marcudlcv.f1uigaragecompose.ui.theme.McLaren
import com.marcudlcv.f1uigaragecompose.ui.theme.McLarenAccent

@Composable
fun ButtonsScreen() {

    val context = LocalContext.current

    var drsEnabled by remember {
        mutableStateOf(false)
    }

    var telemetryEnabled by remember {
        mutableStateOf(false)
    }

    var strategyLoading by remember {
        mutableStateOf(false)
    }

    var status by remember {
        mutableStateOf("Centro de control disponible.")
    }

    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GarageBackground),
        contentPadding = PaddingValues(20.dp),
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
                        text = "MCLAREN · SECCIÓN 02",
                        color = McLaren,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Botones y controles",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Ejecuta acciones y modifica el estado de la interfaz mediante diferentes controles interactivos.",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(McLaren)
                )
            }
        }


        // =================================
        // 1. BUTTON
        // =================================

        item {

            ComponentDocumentation(
                title = "1. Button",
                description = "Button representa una acción principal. Su bloque onClick se ejecuta cuando el usuario presiona el componente."
            )

            Button(
                onClick = {

                    status =
                        "La sesión de carrera fue iniciada."

                    Toast.makeText(
                        context,
                        "Carrera iniciada",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = McLarenAccent
                )
            ) {
                Text("Iniciar carrera")
            }
        }


        // =================================
        // 2. FILLED TONAL BUTTON
        // =================================

        item {

            ComponentDocumentation(
                title = "2. FilledTonalButton",
                description = "FilledTonalButton presenta una acción con menor énfasis visual que el botón principal y puede utilizarse para acciones secundarias."
            )

            FilledTonalButton(
                onClick = {

                    status =
                        "Sesión de clasificación seleccionada."
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar clasificación")
            }
        }


        // =================================
        // 3. ICONBUTTON
        // =================================

        item {

            ComponentDocumentation(
                title = "3. IconButton",
                description = "IconButton ejecuta una acción mediante un área compacta de interacción. Es apropiado para herramientas y acciones rápidas dentro de una interfaz."
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                IconButton(
                    onClick = {

                        status =
                            "Radio del piloto activada."

                        Toast.makeText(
                            context,
                            "Radio: Box, box",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {

                    Text(
                        text = "R",
                        color = McLarenAccent,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        // =================================
        // 4. FLOATING ACTION BUTTON
        // =================================

        item {

            ComponentDocumentation(
                title = "4. FloatingActionButton",
                description = "FloatingActionButton destaca una acción importante mediante un botón flotante que permanece visualmente separado del contenido."
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                FloatingActionButton(
                    onClick = {

                        status =
                            "Acción rápida ejecutada."
                    },
                    containerColor = McLaren
                ) {

                    Text(
                        text = "+",
                        color = GarageHeader,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        // =================================
        // 5. CONTROL TIPO TOGGLE
        // =================================

        item {

            ComponentDocumentation(
                title = "5. Control de estado",
                description = "Compose puede representar un control de dos estados utilizando una variable booleana. En este ejemplo activamos o desactivamos el DRS."
            )

            Button(
                onClick = {

                    drsEnabled = !drsEnabled

                    status =
                        if (drsEnabled) {
                            "DRS activado."
                        } else {
                            "DRS desactivado."
                        }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (drsEnabled) {
                            McLarenAccent
                        } else {
                            GarageHeader
                        }
                )
            ) {

                Text(
                    text =
                        if (drsEnabled) {
                            "DRS: ACTIVADO"
                        } else {
                            "DRS: DESACTIVADO"
                        }
                )
            }
        }


        // =================================
        // 6. SWITCH
        // =================================

        item {

            ComponentDocumentation(
                title = "6. Switch",
                description = "Switch representa una configuración binaria. Su propiedad checked refleja directamente el estado almacenado por Compose."
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GarageSurface
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Telemetría en vivo",
                            color = GarageTextPrimary,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text =
                                if (telemetryEnabled) {
                                    "Transmisión activa"
                                } else {
                                    "Transmisión desactivada"
                                },
                            color = GarageTextSecondary,
                            fontSize = 13.sp
                        )
                    }

                    Switch(
                        checked = telemetryEnabled,
                        onCheckedChange = { enabled ->

                            telemetryEnabled = enabled

                            status =
                                if (enabled) {
                                    "Telemetría activada."
                                } else {
                                    "Telemetría desactivada."
                                }
                        }
                    )
                }
            }
        }


        // =================================
        // 7. BOTÓN CON CARGA
        // =================================

        item {

            ComponentDocumentation(
                title = "7. Botón con estado de carga",
                description = "El contenido del botón puede cambiar según el estado. Aquí una coroutine simula el cálculo de una estrategia durante 2.5 segundos."
            )

            Button(
                onClick = {

                    if (!strategyLoading) {

                        strategyLoading = true

                        status =
                            "Calculando estrategia..."

                        coroutineScope.launch {

                            delay(2500)

                            strategyLoading = false

                            status =
                                "Estrategia calculada correctamente."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !strategyLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = McLarenAccent
                )
            ) {

                if (strategyLoading) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Text("Calculando...")

                } else {

                    Text("Calcular estrategia")
                }
            }
        }


        // =================================
        // 8. BOTÓN DESHABILITADO
        // =================================

        item {

            ComponentDocumentation(
                title = "8. Button deshabilitado",
                description = "La propiedad enabled controla si el componente puede recibir interacción. Un botón deshabilitado comunica que la acción no está disponible."
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text("Control bloqueado")
            }
        }


        // =================================
        // ESTADO GENERAL
        // =================================

        item {

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GarageSurface
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "ESTADO DEL CENTRO DE CONTROL",
                        color = McLarenAccent,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = status,
                        color = GarageTextPrimary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}


@Composable
private fun ComponentDocumentation(
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