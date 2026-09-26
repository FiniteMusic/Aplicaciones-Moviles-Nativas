package com.marcudlcv.f1uigaragecompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.marcudlcv.f1uigaragecompose.data.Driver
import com.marcudlcv.f1uigaragecompose.data.DriverRepository
import com.marcudlcv.f1uigaragecompose.ui.theme.*

@Composable
fun ListsScreen() {

    val drivers = DriverRepository.drivers

    var selectedTeam by remember {
        mutableStateOf<String?>(null)
    }

    val teams = listOf(
        "Ferrari",
        "McLaren",
        "Mercedes",
        "Red Bull Racing",
        "Aston Martin",
        "Alpine",
        "Williams",
        "Haas",
        "Racing Bulls",
        "Audi",
        "Cadillac"
    )

    val filteredDrivers =
        if (selectedTeam == null) {
            drivers
        } else {
            drivers.filter {
                it.team == selectedTeam
            }
        }

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
                        text = "WILLIAMS · SECCIÓN 04",
                        color = Williams,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Listas",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Organiza colecciones de datos mediante componentes de listas y contenido generado dinámicamente.",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Williams)
                )
            }
        }


        // =================================
        // 1. LISTA SIMPLE
        // =================================

        item {

            ListDocumentation(
                title = "1. Lista simple",
                description = "LazyColumn representa listas verticales y compone únicamente los elementos necesarios para mostrar el contenido visible."
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = GarageSurface
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    listOf(
                        "Gran Premio",
                        "Clasificación",
                        "Sprint",
                        "Prácticas libres"
                    ).forEachIndexed { index, session ->

                        Text(
                            text = "${index + 1}. $session",
                            modifier = Modifier.padding(
                                vertical = 7.dp
                            ),
                            color = GarageTextPrimary
                        )

                        if (index < 3) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }


        // =================================
        // 2. LAZYROW
        // =================================

        item {

            ListDocumentation(
                title = "2. LazyRow",
                description = "LazyRow organiza elementos horizontalmente. En este ejemplo permite recorrer las escuderías disponibles."
            )

            LazyRow(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(teams) { team ->

                    Surface(
                        modifier = Modifier
                            .width(135.dp)
                            .clickable {
                                selectedTeam =
                                    if (selectedTeam == team) {
                                        null
                                    } else {
                                        team
                                    }
                            },
                        shape = RoundedCornerShape(14.dp),
                        color =
                            if (selectedTeam == team) {
                                Williams
                            } else {
                                GarageSurface
                            },
                        tonalElevation = 2.dp
                    ) {

                        Text(
                            text = team,
                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 18.dp
                            ),
                            color =
                                if (selectedTeam == team) {
                                    Color.White
                                } else {
                                    GarageTextPrimary
                                },
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    if (selectedTeam == null) {
                        "Filtro: todas las escuderías"
                    } else {
                        "Filtro: $selectedTeam"
                    },
                color = GarageTextSecondary,
                fontSize = 13.sp
            )
        }


        // =================================
        // 3. LISTA DINÁMICA
        // =================================

        item {

            ListDocumentation(
                title = "3. LazyColumn con datos dinámicos",
                description = "Los pilotos registrados en Ferrari se almacenan en un estado observable. Al modificarse la colección, Compose actualiza automáticamente esta interfaz."
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Pilotos registrados",
                        color = GarageTextPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${filteredDrivers.size} encontrados",
                        color = GarageTextSecondary,
                        fontSize = 13.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Williams
                ) {

                    Text(
                        text = drivers.size.toString(),
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 6.dp
                        ),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        // =================================
        // PILOTOS
        // =================================

        if (filteredDrivers.isEmpty()) {

            item {

                EmptyDriversCard(
                    filtered = selectedTeam != null
                )
            }

        } else {

            itemsIndexed(
                items = filteredDrivers,
                key = { _, driver ->
                    driver.number
                }
            ) { index, driver ->

                DriverCard(
                    position = index + 1,
                    driver = driver,
                    onDelete = {
                        DriverRepository.removeDriver(driver)
                    }
                )
            }
        }


        // =================================
        // 4. GRID
        // =================================

        item {

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            ListDocumentation(
                title = "4. Grid",
                description = "Compose permite construir distribuciones en cuadrícula. Aquí representamos las once escuderías mediante filas con dos elementos."
            )

            teams
                .chunked(2)
                .forEach { rowTeams ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        rowTeams.forEach { team ->

                            TeamGridItem(
                                team = team,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (rowTeams.size == 1) {

                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                }
        }


        // =================================
        // ESTADO
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
                        text = "PADDOCK",
                        color = Williams,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = if (drivers.isEmpty()) {
                            "Aún no hay pilotos registrados. Registra uno desde la sección Ferrari."
                        } else {
                            "${drivers.size} piloto(s) registrado(s) actualmente."
                        },
                        color = Color.White,
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
private fun DriverCard(
    position: Int,
    driver: Driver,
    onDelete: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = GarageSurface,
        tonalElevation = 2.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = Williams
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "#${driver.number}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = driver.name,
                    color = GarageTextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = driver.team,
                    color = GarageTextSecondary,
                    fontSize = 13.sp
                )

                Text(
                    text = "Posición en lista: $position",
                    color = GarageTextSecondary,
                    fontSize = 12.sp
                )
            }

            TextButton(
                onClick = onDelete
            ) {
                Text(
                    text = "Eliminar",
                    color = Williams
                )
            }
        }
    }
}


@Composable
private fun EmptyDriversCard(
    filtered: Boolean
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = GarageSurface
    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (filtered) {
                    "Sin pilotos en esta escudería"
                } else {
                    "Sin pilotos registrados"
                },
                color = GarageTextPrimary,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = if (filtered) {
                    "Selecciona nuevamente la escudería para quitar el filtro."
                } else {
                    "Utiliza Ferrari → Entradas de texto para registrar el primer piloto."
                },
                color = GarageTextSecondary,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun TeamGridItem(
    team: String,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = GarageSurface,
        tonalElevation = 2.dp
    ) {

        Box(
            modifier = Modifier
                .height(80.dp)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = team,
                color = GarageTextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun ListDocumentation(
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