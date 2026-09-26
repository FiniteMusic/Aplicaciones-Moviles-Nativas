package com.marcudlcv.f1uigaragecompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.f1uigaragecompose.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen() {

    var softTyre by remember { mutableStateOf(false) }
    var mediumTyre by remember { mutableStateOf(true) }
    var hardTyre by remember { mutableStateOf(false) }

    var selectedStrategy by remember {
        mutableStateOf("Una parada")
    }

    var selectedCircuit by remember {
        mutableStateOf("Monza")
    }

    var circuitExpanded by remember {
        mutableStateOf(false)
    }

    var automaticMode by remember {
        mutableStateOf(false)
    }

    var brakeBalance by remember {
        mutableFloatStateOf(55f)
    }

    var rating by remember {
        mutableIntStateOf(3)
    }

    var selectedWeather by remember {
        mutableStateOf("Seco")
    }

    val circuits = listOf(
        "Monza",
        "Silverstone",
        "Spa-Francorchamps",
        "Suzuka",
        "Interlagos",
        "Mónaco"
    )

    val strategies = listOf(
        "Una parada",
        "Dos paradas",
        "Tres paradas"
    )

    val weatherOptions = listOf(
        "Seco",
        "Lluvia",
        "Mixto"
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
                        text = "MERCEDES · SECCIÓN 03",
                        color = Mercedes,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Selecciones",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Selecciona configuraciones de carrera mediante controles de estado y elección.",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Mercedes)
                )
            }
        }

        // =================================
        // 1. CHECKBOX
        // =================================

        item {

            SelectionDocumentation(
                title = "1. Checkbox",
                description = "Checkbox representa opciones independientes. Cada casilla mantiene su propio estado y varias pueden permanecer seleccionadas simultáneamente."
            )

            SelectionCheckbox(
                text = "Neumático blando",
                checked = softTyre,
                onCheckedChange = {
                    softTyre = it
                }
            )

            SelectionCheckbox(
                text = "Neumático medio",
                checked = mediumTyre,
                onCheckedChange = {
                    mediumTyre = it
                }
            )

            SelectionCheckbox(
                text = "Neumático duro",
                checked = hardTyre,
                onCheckedChange = {
                    hardTyre = it
                }
            )
        }

        // =================================
        // 2. RADIOBUTTON
        // =================================

        item {

            SelectionDocumentation(
                title = "2. RadioButton",
                description = "RadioButton se utiliza cuando las opciones son mutuamente excluyentes. Solo una estrategia puede permanecer seleccionada."
            )

            strategies.forEach { strategy ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedStrategy = strategy
                        }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = selectedStrategy == strategy,
                        onClick = {
                            selectedStrategy = strategy
                        }
                    )

                    Text(
                        text = strategy,
                        color = GarageTextPrimary
                    )
                }
            }
        }

        // =================================
        // 3. DROPDOWN
        // =================================

        item {

            SelectionDocumentation(
                title = "3. Menú desplegable",
                description = "ExposedDropdownMenuBox permite seleccionar un elemento de una lista sin ocupar permanentemente espacio para todas sus opciones."
            )

            ExposedDropdownMenuBox(
                expanded = circuitExpanded,
                onExpandedChange = {
                    circuitExpanded = !circuitExpanded
                }
            ) {

                OutlinedTextField(
                    value = selectedCircuit,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = {
                        Text("Circuito")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = circuitExpanded
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = circuitExpanded,
                    onDismissRequest = {
                        circuitExpanded = false
                    }
                ) {

                    circuits.forEach { circuit ->

                        DropdownMenuItem(
                            text = {
                                Text(circuit)
                            },
                            onClick = {

                                selectedCircuit = circuit
                                circuitExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // =================================
        // 4. SWITCH
        // =================================

        item {

            SelectionDocumentation(
                title = "4. Switch",
                description = "Switch representa una configuración binaria. El estado checked determina visualmente si el modo está activo o desactivado."
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
                            text = "Estrategia automática",
                            color = GarageTextPrimary,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = if (automaticMode) {
                                "Activada"
                            } else {
                                "Desactivada"
                            },
                            color = GarageTextSecondary,
                            fontSize = 13.sp
                        )
                    }

                    Switch(
                        checked = automaticMode,
                        onCheckedChange = {
                            automaticMode = it
                        }
                    )
                }
            }
        }

        // =================================
        // 5. SLIDER
        // =================================

        item {

            SelectionDocumentation(
                title = "5. Slider",
                description = "Slider permite seleccionar un valor dentro de un intervalo continuo. En este ejemplo modifica el balance de frenado entre 45% y 65%."
            )

            Text(
                text = "Balance de frenado: ${brakeBalance.toInt()}%",
                color = GarageTextPrimary,
                fontWeight = FontWeight.Bold
            )

            Slider(
                value = brakeBalance,
                onValueChange = {
                    brakeBalance = it
                },
                valueRange = 45f..65f,
                steps = 19,
                colors = SliderDefaults.colors(
                    thumbColor = Mercedes,
                    activeTrackColor = Mercedes
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "45%",
                    color = GarageTextSecondary,
                    fontSize = 12.sp
                )

                Text(
                    text = "65%",
                    color = GarageTextSecondary,
                    fontSize = 12.sp
                )
            }
        }

        // =================================
        // 6. RATING EQUIVALENTE
        // =================================

        item {

            SelectionDocumentation(
                title = "6. Valoración",
                description = "Compose Material 3 no incluye un RatingBar estándar. Podemos construir un componente equivalente combinando elementos interactivos y estado."
            )

            Text(
                text = "Nivel de confianza: $rating / 5",
                color = GarageTextPrimary,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                for (value in 1..5) {

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                rating = value
                            },
                        shape = RoundedCornerShape(10.dp),
                        color = if (value <= rating) {
                            Mercedes
                        } else {
                            GarageSurface
                        },
                        tonalElevation = 2.dp
                    ) {

                        Text(
                            text = value.toString(),
                            modifier = Modifier.padding(
                                vertical = 12.dp
                            ),
                            color = if (value <= rating) {
                                GarageHeader
                            } else {
                                GarageTextSecondary
                            },
                            fontWeight = FontWeight.Bold,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }

        // =================================
        // 7. FILTERCHIP
        // =================================

        item {

            SelectionDocumentation(
                title = "7. FilterChip",
                description = "FilterChip representa filtros compactos. El estado selected permite destacar visualmente la condición meteorológica elegida."
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                weatherOptions.forEach { weather ->

                    FilterChip(
                        selected = selectedWeather == weather,
                        onClick = {
                            selectedWeather = weather
                        },
                        label = {
                            Text(weather)
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Mercedes,
                            selectedLabelColor = GarageHeader
                        )
                    )
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
                        text = "CONFIGURACIÓN DE CARRERA",
                        color = Mercedes,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    SummaryLine(
                        label = "Circuito",
                        value = selectedCircuit
                    )

                    SummaryLine(
                        label = "Estrategia",
                        value = selectedStrategy
                    )

                    SummaryLine(
                        label = "Clima",
                        value = selectedWeather
                    )

                    SummaryLine(
                        label = "Balance",
                        value = "${brakeBalance.toInt()}%"
                    )

                    SummaryLine(
                        label = "Confianza",
                        value = "$rating / 5"
                    )

                    SummaryLine(
                        label = "Automático",
                        value = if (automaticMode) {
                            "Sí"
                        } else {
                            "No"
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Compuestos habilitados: ${
                            buildList {
                                if (softTyre) add("Blando")
                                if (mediumTyre) add("Medio")
                                if (hardTyre) add("Duro")
                            }.ifEmpty {
                                listOf("Ninguno")
                            }.joinToString(", ")
                        }",
                        color = Color.White,
                        fontSize = 13.sp
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
private fun SelectionCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCheckedChange(!checked)
            }
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Mercedes
            )
        )

        Text(
            text = text,
            color = GarageTextPrimary
        )
    }
}


@Composable
private fun SelectionDocumentation(
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


@Composable
private fun SummaryLine(
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