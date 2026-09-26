package com.marcudlcv.f1uigarageviews.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton

import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

import com.marcudlcv.f1uigarageviews.R

class ButtonsFragment : Fragment(R.layout.fragment_buttons) {

    private val handler = Handler(Looper.getMainLooper())

    private var quickActionCount = 0

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val status = view.findViewById<TextView>(
            R.id.textControlStatus
        )

        // 1. Button

        val startRace = view.findViewById<Button>(
            R.id.buttonStartRace
        )

        startRace.setOnClickListener {

            status.text = "Carrera iniciada. Semáforo en verde."
        }

        // 2. MaterialButton

        val qualifying = view.findViewById<MaterialButton>(
            R.id.buttonQualifying
        )

        qualifying.setOnClickListener {

            status.text = "Modo clasificación activado."
        }

        // 3. ImageButton

        val radio = view.findViewById<ImageButton>(
            R.id.buttonRadio
        )

        radio.setOnClickListener {

            status.text = "Mensaje enviado por radio al piloto."
        }

        // 4. FloatingActionButton

        val quickAction = view.findViewById<FloatingActionButton>(
            R.id.fabQuickAction
        )

        quickAction.setOnClickListener {

            quickActionCount++

            status.text =
                "Acciones rápidas registradas: $quickActionCount"
        }

        // 5. ToggleButton

        val drs = view.findViewById<ToggleButton>(
            R.id.toggleDrs
        )

        drs.setOnCheckedChangeListener { _, isChecked ->

            status.text = if (isChecked) {

                "DRS activado."

            } else {

                "DRS desactivado."
            }
        }

        // 6. SwitchMaterial

        val telemetry = view.findViewById<SwitchMaterial>(
            R.id.switchTelemetry
        )

        telemetry.setOnCheckedChangeListener { _, isChecked ->

            status.text = if (isChecked) {

                "Telemetría habilitada."

            } else {

                "Telemetría deshabilitada."
            }
        }

        // 7. Botón con carga

        val strategy = view.findViewById<MaterialButton>(
            R.id.buttonStrategy
        )

        val progress = view.findViewById<ProgressBar>(
            R.id.progressStrategy
        )

        strategy.setOnClickListener {

            strategy.isEnabled = false

            strategy.text = "Procesando..."

            progress.visibility = View.VISIBLE

            status.text = "Analizando estrategia de carrera..."

            handler.postDelayed({

                // Evita actualizar vistas que ya no están activas.

                if (this.view === view) {

                    progress.visibility = View.GONE

                    strategy.isEnabled = true

                    strategy.text = "Procesar estrategia"

                    status.text =
                        "Estrategia procesada correctamente."
                }

            }, 2500)
        }

        // 8. Botón deshabilitado

        val disabled = view.findViewById<MaterialButton>(
            R.id.buttonDisabled
        )

        disabled.isEnabled = false
    }
}