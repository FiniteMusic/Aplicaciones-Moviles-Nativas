package com.marcudlcv.f1uigarageviews.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.marcudlcv.f1uigarageviews.data.Driver
import com.marcudlcv.f1uigarageviews.data.DriverRepository

import com.marcudlcv.f1uigarageviews.R

class TextInputFragment : Fragment(R.layout.fragment_text_input) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        setupValidation(view)

        setupTeamDropdown(view)

        setupSearch(view)

        setupRegisterButton(view)
    }

    // Validación del número del piloto

    private fun setupValidation(view: View) {

        val numberLayout =
            view.findViewById<TextInputLayout>(
                R.id.layoutDriverNumber
            )

        val numberInput =
            view.findViewById<TextInputEditText>(
                R.id.inputDriverNumber
            )

        val validateButton =
            view.findViewById<MaterialButton>(
                R.id.buttonValidate
            )

        validateButton.setOnClickListener {

            val number =
                numberInput.text.toString().toIntOrNull()

            when {

                number == null -> {

                    numberLayout.error =
                        "Introduce un número válido"
                }

                number !in 1..99 -> {

                    numberLayout.error =
                        "El número debe estar entre 1 y 99"
                }

                else -> {

                    numberLayout.error = null

                    Toast.makeText(
                        requireContext(),
                        "Número de piloto válido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // Menú desplegable de escuderías

    private fun setupTeamDropdown(view: View) {

        val teamInput =
            view.findViewById<MaterialAutoCompleteTextView>(
                R.id.inputTeam
            )

        val teams = arrayOf(

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

        val adapter =
            android.widget.ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                teams
            )

        teamInput.setAdapter(adapter)
    }

    // Búsqueda interactiva

    private fun setupSearch(view: View) {

        val searchInput =
            view.findViewById<TextInputEditText>(
                R.id.inputSearch
            )

        val searchResult =
            view.findViewById<TextView>(
                R.id.textSearchResult
            )

        val drivers = listOf(

            "Max Verstappen",

            "Sergio(Checo) Perez",

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

        searchInput.addTextChangedListener(

            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    val query =
                        s.toString().trim()

                    if (query.isEmpty()) {

                        searchResult.text =
                            "Escribe para buscar un piloto."

                        return
                    }

                    val results =
                        drivers.filter {

                            it.contains(
                                query,
                                ignoreCase = true
                            )
                        }

                    searchResult.text =
                        if (results.isEmpty()) {

                            "No se encontraron pilotos."

                        } else {

                            results.joinToString("\n")
                        }
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {}
            }
        )
    }

    // Registro provisional

    // Registro de pilotos en el repositorio compartido

    private fun setupRegisterButton(view: View) {

        // =================================
        // REFERENCIAS A LOS COMPONENTES
        // =================================

        val registerButton =
            view.findViewById<MaterialButton>(
                R.id.buttonRegisterDriver
            )

        val nameInput =
            view.findViewById<TextInputEditText>(
                R.id.inputDriverName
            )

        val numberInput =
            view.findViewById<TextInputEditText>(
                R.id.inputDriverNumber
            )

        val teamInput =
            view.findViewById<MaterialAutoCompleteTextView>(
                R.id.inputTeam
            )

        val numberLayout =
            view.findViewById<TextInputLayout>(
                R.id.layoutDriverNumber
            )

        val nameLayout =
            view.findViewById<TextInputLayout>(
                R.id.layoutDriverName
            )

        val teamLayout =
            view.findViewById<TextInputLayout>(
                R.id.layoutTeam
            )


        // =================================
        // EVENTO DEL BOTÓN
        // =================================

        registerButton.setOnClickListener {

            val name =
                nameInput.text.toString().trim()

            val number =
                numberInput.text.toString().toIntOrNull()

            val team =
                teamInput.text.toString().trim()


            // =================================
            // VALIDAR NOMBRE
            // =================================

            if (name.isEmpty()) {

                nameLayout.error =
                    "Introduce el nombre del piloto"

                nameInput.requestFocus()

                return@setOnClickListener
            }

            nameLayout.error = null


            // =================================
            // VALIDAR NÚMERO
            // =================================

            if (number == null || number !in 1..99) {

                numberLayout.error =
                    "Introduce un número entre 1 y 99"

                numberInput.requestFocus()

                return@setOnClickListener
            }

            numberLayout.error = null


            // =================================
            // VALIDAR ESCUDERÍA
            // =================================

            if (team.isEmpty()) {

                teamLayout.error =
                    "Selecciona una escudería"

                teamInput.requestFocus()

                return@setOnClickListener
            }

            teamLayout.error = null


            // =================================
            // CREAR OBJETO DRIVER
            // =================================

            val driver = Driver(
                name = name,
                number = number,
                team = team
            )


            // =================================
            // REGISTRAR EN EL REPOSITORIO
            // =================================

            val registered =
                DriverRepository.addDriver(driver)


            // =================================
            // RESULTADO DEL REGISTRO
            // =================================

            if (registered) {

                Toast.makeText(
                    requireContext(),
                    "Piloto registrado: $name - #$number - $team",
                    Toast.LENGTH_LONG
                ).show()


                // Limpiar los campos utilizados.

                nameInput.text?.clear()

                numberInput.text?.clear()

                teamInput.setText("", false)


                // Limpiar mensajes de error.

                nameLayout.error = null

                numberLayout.error = null

                teamLayout.error = null

            } else {

                numberLayout.error =
                    "Ya existe un piloto con este número"

                Toast.makeText(
                    requireContext(),
                    "El número #$number ya está registrado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}