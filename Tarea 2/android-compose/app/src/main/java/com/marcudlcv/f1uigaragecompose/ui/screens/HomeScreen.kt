package com.marcudlcv.f1uigaragecompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.f1uigaragecompose.navigation.GarageRoutes
import com.marcudlcv.f1uigaragecompose.ui.components.TeamCategoryCard
import com.marcudlcv.f1uigaragecompose.ui.theme.*

private data class GarageCategory(
    val team: String,
    val title: String,
    val description: String,
    val color: Color,
    val route: String
)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {

    val categories = listOf(
        GarageCategory(
            "Ferrari",
            "Entradas de texto",
            "Campos de texto, validación, búsqueda y registro de pilotos.",
            Ferrari,
            GarageRoutes.TEXT_INPUT
        ),
        GarageCategory(
            "McLaren",
            "Botones y controles",
            "Acciones, botones, interruptores y controles interactivos.",
            McLaren,
            GarageRoutes.BUTTONS
        ),
        GarageCategory(
            "Mercedes",
            "Selecciones",
            "Casillas, opciones, listas desplegables y controles de selección.",
            Mercedes,
            GarageRoutes.SELECTION
        ),
        GarageCategory(
            "Williams",
            "Listas",
            "Visualización de pilotos y escuderías mediante diferentes listas.",
            Williams,
            GarageRoutes.LISTS
        ),
        GarageCategory(
            "Aston Martin",
            "Retroalimentación",
            "Mensajes, diálogos, errores e indicadores de progreso.",
            AstonMartin,
            GarageRoutes.FEEDBACK
        ),
        GarageCategory(
            "Alpine",
            "Layouts",
            "Distribución y organización de elementos de la interfaz.",
            Alpine,
            GarageRoutes.LAYOUTS
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GarageBackground),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

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
                        text = "F1 UI GARAGE",
                        color = Ferrari,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Catálogo de componentes",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Jetpack Compose · Kotlin",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                ) {

                    listOf(
                        Ferrari,
                        McLaren,
                        Mercedes,
                        Williams,
                        AstonMartin,
                        Alpine
                    ).forEach { color ->

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(color)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "SELECCIONA UNA SECCIÓN",
                color = GarageTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(categories) { category ->

            TeamCategoryCard(
                team = category.team,
                title = category.title,
                description = category.description,
                accentColor = category.color,
                onClick = {
                    onNavigate(category.route)
                }
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }
    }
}