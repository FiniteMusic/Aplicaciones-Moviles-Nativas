package com.marcudlcv.f1uigaragecompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.f1uigaragecompose.ui.theme.Ferrari
import com.marcudlcv.f1uigaragecompose.ui.theme.FerrariAccent
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageBackground
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageHeader
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageTextPrimary
import com.marcudlcv.f1uigaragecompose.ui.theme.GarageTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputScreen() {

    val context = LocalContext.current

    var driverName by remember {
        mutableStateOf("")
    }

    var driverNumber by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var observations by remember {
        mutableStateOf("")
    }

    var selectedTeam by remember {
        mutableStateOf("")
    }

    var teamMenuExpanded by remember {
        mutableStateOf(false)
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    var numberError by remember {
        mutableStateOf<String?>(null)
    }

    var nameError by remember {
        mutableStateOf<String?>(null)
    }

    var teamError by remember {
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

    val drivers = listOf(
        "Max Verstappen",
        "Lewis Hamilton",
        "Charles Leclerc",
        "Lando Norris",
        "Oscar Piastri",
        "George Russell",
        "Fernando Alonso",
        "Carlos Sainz",
        "Alexander Albon",
        "Pierre Gasly"
    )

    val searchResults = remember(searchQuery) {

        if (searchQuery.isBlank()) {

            emptyList()

        } else {

            drivers.filter {
                it.contains(
                    searchQuery.trim(),
                    ignoreCase = true
                )
            }
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
                        text = "FERRARI · SECCIÓN 01",
                        color = Ferrari,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Entradas de texto",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Captura, valida y procesa información mediante componentes de entrada de Jetpack Compose.",
                        color = Color(0xFFD5D8DF),
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Ferrari)
                )
            }
        }


        // =================================
        // 1. NOMBRE
        // =================================

        item {

            ComponentTitle(
                title = "1. Nombre del piloto",
                description = "OutlinedTextField permite capturar texto y mantener su valor mediante estado. En Compose, el componente se actualiza cuando cambia dicho estado."
            )

            OutlinedTextField(
                value = driverName,
                onValueChange = {
                    driverName = it
                    nameError = null
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nombre del piloto")
                },
                singleLine = true,
                isError = nameError != null,
                supportingText = {
                    nameError?.let {
                        Text(it)
                    }
                }
            )
        }


        // =================================
        // 2. NÚMERO
        // =================================

        item {

            ComponentTitle(
                title = "2. Entrada numérica y validación",
                description = "KeyboardOptions solicita un teclado numérico. El valor se valida para comprobar que el dorsal del piloto se encuentre entre 1 y 99."
            )

            OutlinedTextField(
                value = driverNumber,
                onValueChange = {
                    driverNumber = it
                    numberError = null
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Número del piloto")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                isError = numberError != null,
                supportingText = {
                    numberError?.let {
                        Text(it)
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {

                    val number =
                        driverNumber.toIntOrNull()

                    numberError = when {

                        number == null ->
                            "Introduce un número válido"

                        number !in 1..99 ->
                            "El número debe estar entre 1 y 99"

                        else ->
                            null
                    }

                    if (numberError == null) {

                        Toast.makeText(
                            context,
                            "Número de piloto válido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FerrariAccent
                )
            ) {

                Text("Validar número")
            }
        }


        // =================================
        // 3. CONTRASEÑA
        // =================================

        item {

            ComponentTitle(
                title = "3. Contraseña",
                description = "PasswordVisualTransformation oculta visualmente los caracteres introducidos sin modificar el valor almacenado en el estado."
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Contraseña")
                },
                visualTransformation =
                    PasswordVisualTransformation(),
                singleLine = true
            )
        }


        // =================================
        // 4. CORREO Y TELÉFONO
        // =================================

        item {

            ComponentTitle(
                title = "4. Tipos de teclado",
                description = "KeyboardOptions permite solicitar teclados especializados según el dato esperado, como correo electrónico o número telefónico."
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Correo electrónico")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Teléfono")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true
            )
        }


        // =================================
        // 5. TEXTO MULTILÍNEA
        // =================================

        item {

            ComponentTitle(
                title = "5. Texto multilínea",
                description = "Un OutlinedTextField puede admitir varias líneas para capturar contenido más extenso, como observaciones o comentarios."
            )

            OutlinedTextField(
                value = observations,
                onValueChange = {
                    observations = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                label = {
                    Text("Observaciones")
                },
                minLines = 3,
                maxLines = 5
            )
        }


        // =================================
        // 6. DROPDOWN
        // =================================

        item {

            ComponentTitle(
                title = "6. Menú desplegable",
                description = "ExposedDropdownMenuBox combina un campo de texto con una lista de opciones para seleccionar una escudería."
            )

            ExposedDropdownMenuBox(
                expanded = teamMenuExpanded,
                onExpandedChange = {
                    teamMenuExpanded = !teamMenuExpanded
                }
            ) {

                OutlinedTextField(
                    value = selectedTeam,
                    onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    label = {
                        Text("Escudería")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = teamMenuExpanded
                        )
                    },
                    isError = teamError != null,
                    supportingText = {
                        teamError?.let {
                            Text(it)
                        }
                    }
                )

                ExposedDropdownMenu(
                    expanded = teamMenuExpanded,
                    onDismissRequest = {
                        teamMenuExpanded = false
                    }
                ) {

                    teams.forEach { team ->

                        DropdownMenuItem(
                            text = {
                                Text(team)
                            },
                            onClick = {

                                selectedTeam = team

                                teamError = null

                                teamMenuExpanded = false
                            }
                        )
                    }
                }
            }
        }


        // =================================
        // 7. BÚSQUEDA
        // =================================

        item {

            ComponentTitle(
                title = "7. Búsqueda interactiva",
                description = "El resultado se deriva directamente del estado del campo. Cada cambio provoca una recomposición con los pilotos coincidentes."
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Buscar piloto")
                },
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = when {

                    searchQuery.isBlank() ->
                        "Escribe para buscar un piloto."

                    searchResults.isEmpty() ->
                        "No se encontraron pilotos."

                    else ->
                        searchResults.joinToString("\n")
                },
                color = GarageTextSecondary,
                fontSize = 14.sp
            )
        }


        // =================================
        // 8. REGISTRO
        // =================================

        item {

            ComponentTitle(
                title = "8. Registro del piloto",
                description = "El botón utiliza los estados anteriores para validar la información. Posteriormente compartiremos estos datos con la pantalla de listas."
            )

            Button(
                onClick = {

                    val number =
                        driverNumber.toIntOrNull()

                    nameError =
                        if (driverName.isBlank()) {
                            "Introduce el nombre del piloto"
                        } else {
                            null
                        }

                    numberError =
                        if (
                            number == null ||
                            number !in 1..99
                        ) {
                            "Introduce un número entre 1 y 99"
                        } else {
                            null
                        }

                    teamError =
                        if (selectedTeam.isBlank()) {
                            "Selecciona una escudería"
                        } else {
                            null
                        }

                    if (
                        nameError == null &&
                        numberError == null &&
                        teamError == null
                    ) {

                        Toast.makeText(
                            context,
                            "Piloto validado: $driverName - #$number - $selectedTeam",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FerrariAccent
                )
            ) {

                Text("Registrar piloto")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}


// =================================
// DOCUMENTACIÓN DE COMPONENTES
// =================================

@Composable
private fun ComponentTitle(
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