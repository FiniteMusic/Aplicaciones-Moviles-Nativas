package com.marcudlcv.f1uigaragecompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.f1uigaragecompose.ui.theme.*

@Composable
fun LayoutsScreen() {

    var verticalLayout by remember {
        mutableStateOf(true)
    }

    var liveBadgeVisible by remember {
        mutableStateOf(true)
    }

    val circuits = listOf(
        "Monza",
        "Silverstone",
        "Spa",
        "Suzuka",
        "Interlagos",
        "Mónaco"
    )

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
                        text = "ALPINE · SECCIÓN 06",
                        color = Alpine,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Layouts",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Organiza componentes mediante las estructuras declarativas de distribución disponibles en Jetpack Compose.",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Alpine)
                )
            }
        }

        // =================================
        // 1. ROW / COLUMN
        // =================================

        item {

            LayoutDocumentation(
                title = "1. Row y Column",
                description = "Column distribuye componentes verticalmente y Row horizontalmente. Son los equivalentes conceptuales principales de LinearLayout."
            )

            Button(
                onClick = {
                    verticalLayout = !verticalLayout
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AlpineAccent
                )
            ) {

                Text(
                    if (verticalLayout) {
                        "Cambiar a horizontal"
                    } else {
                        "Cambiar a vertical"
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GarageSurface
            ) {

                if (verticalLayout) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        LayoutBlock("Motor")
                        LayoutBlock("Aerodinámica")
                        LayoutBlock("Suspensión")
                    }

                } else {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        LayoutBlock(
                            text = "Motor",
                            modifier = Modifier.weight(1f)
                        )

                        LayoutBlock(
                            text = "Aero",
                            modifier = Modifier.weight(1f)
                        )

                        LayoutBlock(
                            text = "Susp.",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // =================================
        // 2. POSICIONAMIENTO
        // =================================

        item {

            LayoutDocumentation(
                title = "2. Posicionamiento con Box",
                description = "Compose utiliza modificadores y Alignment para posicionar elementos. Este ejemplo muestra contenido distribuido en distintas zonas de un contenedor."
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GarageSurface)
                    .padding(16.dp)
            ) {

                Text(
                    text = "P1",
                    modifier = Modifier.align(
                        Alignment.TopStart
                    ),
                    color = AlpineAccent,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "ALPINE",
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    color = GarageTextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "LAP 42 / 57",
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    ),
                    color = GarageTextSecondary,
                    fontSize = 13.sp
                )
            }
        }

        // =================================
        // 3. BOX / SUPERPOSICIÓN
        // =================================

        item {

            LayoutDocumentation(
                title = "3. Box",
                description = "Box permite superponer componentes en el mismo espacio. Es el equivalente conceptual de FrameLayout para interfaces basadas en capas."
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GarageHeader)
                    .clickable {
                        liveBadgeVisible =
                            !liveBadgeVisible
                    }
            ) {

                Column(
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "A524",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "TELEMETRÍA",
                        color = Color(0xFFD5D8DF),
                        fontSize = 12.sp
                    )
                }

                if (liveBadgeVisible) {

                    Surface(
                        modifier = Modifier
                            .align(
                                Alignment.TopEnd
                            )
                            .padding(12.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Alpine
                    ) {

                        Text(
                            text = "EN VIVO",
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            ),
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Toca el panel para mostrar u ocultar la etiqueta EN VIVO.",
                color = GarageTextSecondary,
                fontSize = 12.sp
            )
        }

        // =================================
        // 4. GRID
        // =================================

        item {

            LayoutDocumentation(
                title = "4. Cuadrícula",
                description = "Las filas y columnas pueden combinarse para crear distribuciones bidimensionales. Aquí organizamos datos de telemetría en una cuadrícula de dos columnas."
            )

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    TelemetryCell(
                        label = "VELOCIDAD",
                        value = "312 km/h",
                        modifier = Modifier.weight(1f)
                    )

                    TelemetryCell(
                        label = "RPM",
                        value = "11,450",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    TelemetryCell(
                        label = "MARCHA",
                        value = "8",
                        modifier = Modifier.weight(1f)
                    )

                    TelemetryCell(
                        label = "ERS",
                        value = "74%",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // =================================
        // 5. SCROLL VERTICAL
        // =================================

        item {

            LayoutDocumentation(
                title = "5. Scroll vertical",
                description = "LazyColumn proporciona desplazamiento vertical y composición diferida. De hecho, toda esta pantalla utiliza una LazyColumn como contenedor principal."
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GarageSurface
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "CONTENIDO DE ESTA PANTALLA",
                        color = AlpineAccent,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Desplázate hacia arriba y abajo para comprobar el comportamiento vertical de LazyColumn. Los elementos se organizan secuencialmente sin requerir un ScrollView tradicional.",
                        color = GarageTextSecondary,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // =================================
        // 6. SCROLL HORIZONTAL
        // =================================

        item {

            LayoutDocumentation(
                title = "6. Scroll horizontal",
                description = "LazyRow proporciona desplazamiento horizontal y genera sus elementos de forma diferida, siendo útil para carruseles y colecciones."
            )

            LazyRow(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(circuits) { circuit ->

                    Surface(
                        modifier = Modifier
                            .width(150.dp)
                            .height(90.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = GarageSurface,
                        tonalElevation = 2.dp
                    ) {

                        Box(
                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(
                                text = circuit,
                                modifier = Modifier.padding(
                                    12.dp
                                ),
                                color = GarageTextPrimary,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // =================================
        // 7. HORIZONTALSCROLL
        // =================================

        item {

            LayoutDocumentation(
                title = "7. horizontalScroll",
                description = "El modificador horizontalScroll agrega desplazamiento a un Row convencional. A diferencia de LazyRow, todos sus elementos se componen inmediatamente."
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                for (lap in 1..10) {

                    Surface(
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(14.dp),
                        color = if (lap == 7) {
                            Alpine
                        } else {
                            GarageSurface
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "VUELTA $lap",
                                color = if (lap == 7) {
                                    Color.White
                                } else {
                                    GarageTextSecondary
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "1:2${lap}.420",
                                color = if (lap == 7) {
                                    Color.White
                                } else {
                                    GarageTextPrimary
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // =================================
        // RESUMEN
        // =================================

        item {

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = GarageHeader
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "ESTADO DEL LAYOUT",
                        color = Alpine,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    LayoutStatusLine(
                        label = "Distribución",
                        value = if (verticalLayout) {
                            "Vertical"
                        } else {
                            "Horizontal"
                        }
                    )

                    LayoutStatusLine(
                        label = "Indicador EN VIVO",
                        value = if (liveBadgeVisible) {
                            "Visible"
                        } else {
                            "Oculto"
                        }
                    )

                    LayoutStatusLine(
                        label = "Circuitos",
                        value = circuits.size.toString()
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
private fun LayoutBlock(
    text: String,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Alpine
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(14.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun TelemetryCell(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = GarageSurface,
        tonalElevation = 2.dp
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = label,
                color = AlpineAccent,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = value,
                color = GarageTextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun LayoutStatusLine(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {

        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            color = Color(0xFFD5D8DF),
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun LayoutDocumentation(
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