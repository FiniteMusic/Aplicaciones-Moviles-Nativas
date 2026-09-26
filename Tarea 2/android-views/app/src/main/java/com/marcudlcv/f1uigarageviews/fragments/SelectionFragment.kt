package com.marcudlcv.f1uigarageviews.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView

import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.chip.ChipGroup
import com.google.android.material.switchmaterial.SwitchMaterial

import com.marcudlcv.f1uigarageviews.R

class SelectionFragment : Fragment(R.layout.fragment_selection) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // =================================
        // REFERENCIAS A LOS COMPONENTES
        // =================================

        val checkTires = view.findViewById<MaterialCheckBox>(
            R.id.checkTires
        )

        val checkFuel = view.findViewById<MaterialCheckBox>(
            R.id.checkFuel
        )

        val checkAerodynamics = view.findViewById<MaterialCheckBox>(
            R.id.checkAerodynamics
        )

        val radioGroupTires = view.findViewById<RadioGroup>(
            R.id.radioGroupTires
        )

        val spinnerCircuit = view.findViewById<Spinner>(
            R.id.spinnerCircuit
        )

        val switchEnergySaving = view.findViewById<SwitchMaterial>(
            R.id.switchEnergySaving
        )

        val seekBrakeBalance = view.findViewById<SeekBar>(
            R.id.seekBrakeBalance
        )

        val textBrakeBalance = view.findViewById<TextView>(
            R.id.textBrakeBalance
        )

        val ratingSetup = view.findViewById<RatingBar>(
            R.id.ratingSetup
        )

        val textSetupRating = view.findViewById<TextView>(
            R.id.textSetupRating
        )

        val chipGroupWeather = view.findViewById<ChipGroup>(
            R.id.chipGroupWeather
        )

        val status = view.findViewById<TextView>(
            R.id.textSelectionStatus
        )

        val showConfiguration = view.findViewById<MaterialButton>(
            R.id.buttonShowConfiguration
        )


        // =================================
        // 1. CHECKBOX
        // =================================

        val preparationListener =
            android.widget.CompoundButton.OnCheckedChangeListener {
                    button, isChecked ->

                val task = when (button.id) {

                    R.id.checkTires ->
                        "Revisión de neumáticos"

                    R.id.checkFuel ->
                        "Verificación de combustible"

                    R.id.checkAerodynamics ->
                        "Inspección aerodinámica"

                    else ->
                        "Tarea"
                }

                status.text = if (isChecked) {

                    "$task completada."

                } else {

                    "$task pendiente."
                }
            }

        checkTires.setOnCheckedChangeListener(
            preparationListener
        )

        checkFuel.setOnCheckedChangeListener(
            preparationListener
        )

        checkAerodynamics.setOnCheckedChangeListener(
            preparationListener
        )


        // =================================
        // 2. RADIOGROUP Y RADIOBUTTON
        // =================================

        radioGroupTires.setOnCheckedChangeListener {
                _, checkedId ->

            val selectedTire = when (checkedId) {

                R.id.radioSoft ->
                    "Blando"

                R.id.radioMedium ->
                    "Medio"

                R.id.radioHard ->
                    "Duro"

                else ->
                    "Sin seleccionar"
            }

            status.text =
                "Compuesto seleccionado: $selectedTire"
        }


        // =================================
        // 3. SPINNER
        // =================================

        val circuits = listOf(
            "Selecciona un circuito",
            "Autódromo Hermanos Rodríguez",
            "Silverstone",
            "Monza",
            "Suzuka",
            "Spa-Francorchamps",
            "Mónaco",
            "Interlagos"
        )

        val circuitAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            circuits
        )

        circuitAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerCircuit.adapter = circuitAdapter

        spinnerCircuit.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    selectedView: View?,
                    position: Int,
                    id: Long
                ) {

                    if (position > 0) {

                        status.text =
                            "Circuito seleccionado: ${circuits[position]}"
                    }
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                    // No se requiere una acción.
                }
            }


        // =================================
        // 4. SWITCHMATERIAL
        // =================================

        switchEnergySaving.setOnCheckedChangeListener {
                _, isChecked ->

            status.text = if (isChecked) {

                "Modo de ahorro de energía activado."

            } else {

                "Modo de ahorro de energía desactivado."
            }
        }


        // =================================
        // 5. SEEKBAR
        // =================================

        seekBrakeBalance.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    textBrakeBalance.text =
                        "Balance de frenado: $progress %"

                    if (fromUser) {

                        status.text =
                            "Balance de frenado ajustado a $progress %."
                    }
                }

                override fun onStartTrackingTouch(
                    seekBar: SeekBar?
                ) {
                    // El usuario comienza a mover el control.
                }

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {
                    // El usuario termina de mover el control.
                }
            }
        )


        // =================================
        // 6. RATINGBAR
        // =================================

        ratingSetup.setOnRatingBarChangeListener {
                _, rating, fromUser ->

            textSetupRating.text =
                "Valoración: $rating de 5 estrellas"

            if (fromUser) {

                status.text =
                    "Configuración valorada con $rating estrellas."
            }
        }


        // =================================
        // 7. CHIPGROUP
        // =================================

        chipGroupWeather.setOnCheckedChangeListener {
                _, checkedId ->

            val weather = when (checkedId) {

                R.id.chipDry ->
                    "Pista seca"

                R.id.chipDamp ->
                    "Pista húmeda"

                R.id.chipWet ->
                    "Lluvia"

                else ->
                    "Sin seleccionar"
            }

            status.text =
                "Condición seleccionada: $weather"
        }


        // =================================
        // CONSULTAR CONFIGURACIÓN COMPLETA
        // =================================

        showConfiguration.setOnClickListener {

            // Tareas de preparación

            val completedTasks = listOfNotNull(

                if (checkTires.isChecked)
                    "Neumáticos" else null,

                if (checkFuel.isChecked)
                    "Combustible" else null,

                if (checkAerodynamics.isChecked)
                    "Aerodinámica" else null
            )

            val preparation = if (
                completedTasks.isEmpty()
            ) {

                "Ninguna"

            } else {

                completedTasks.joinToString(", ")
            }


            // Compuesto seleccionado

            val tireCompound = when (
                radioGroupTires.checkedRadioButtonId
            ) {

                R.id.radioSoft ->
                    "Blando"

                R.id.radioMedium ->
                    "Medio"

                R.id.radioHard ->
                    "Duro"

                else ->
                    "Sin seleccionar"
            }


            // Circuito

            val circuit = if (
                spinnerCircuit.selectedItemPosition > 0
            ) {

                spinnerCircuit.selectedItem.toString()

            } else {

                "Sin seleccionar"
            }


            // Ahorro de energía

            val energyMode = if (
                switchEnergySaving.isChecked
            ) {

                "Activado"

            } else {

                "Desactivado"
            }


            // Balance de frenado

            val brakeBalance =
                seekBrakeBalance.progress


            // Valoración

            val rating =
                ratingSetup.rating


            // Condición de pista

            val weather = when (
                chipGroupWeather.checkedChipId
            ) {

                R.id.chipDry ->
                    "Pista seca"

                R.id.chipDamp ->
                    "Pista húmeda"

                R.id.chipWet ->
                    "Lluvia"

                else ->
                    "Sin seleccionar"
            }


            // Mostrar resumen

            status.text = """
                Preparación: $preparation
                
                Neumáticos: $tireCompound
                
                Circuito: $circuit
                
                Ahorro de energía: $energyMode
                
                Balance de frenado: $brakeBalance %
                
                Valoración: $rating / 5
                
                Condición de pista: $weather
            """.trimIndent()
        }
    }
}