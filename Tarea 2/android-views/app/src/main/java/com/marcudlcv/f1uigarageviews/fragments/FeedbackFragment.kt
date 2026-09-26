package com.marcudlcv.f1uigarageviews.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import com.marcudlcv.f1uigarageviews.R

class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private var progressAnimator: ValueAnimator? = null

    private var circularProgressRunnable: Runnable? = null

    private var circularProgressView: View? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // =================================
        // REFERENCIAS
        // =================================

        val status = view.findViewById<TextView>(
            R.id.textFeedbackStatus
        )

        val buttonToast = view.findViewById<MaterialButton>(
            R.id.buttonShowToast
        )

        val buttonSnackbar = view.findViewById<MaterialButton>(
            R.id.buttonShowSnackbar
        )

        val buttonDialog = view.findViewById<MaterialButton>(
            R.id.buttonShowDialog
        )

        val buttonProgress = view.findViewById<MaterialButton>(
            R.id.buttonStartProgress
        )

        val progressTelemetry = view.findViewById<ProgressBar>(
            R.id.progressTelemetry
        )

        val textPercentage = view.findViewById<TextView>(
            R.id.textProgressPercentage
        )

        val buttonCircular = view.findViewById<MaterialButton>(
            R.id.buttonCircularProgress
        )

        val progressCircular = view.findViewById<ProgressBar>(
            R.id.progressCircular
        )

        val radioLayout = view.findViewById<TextInputLayout>(
            R.id.layoutRadioCode
        )

        val radioInput = view.findViewById<TextInputEditText>(
            R.id.inputRadioCode
        )

        val buttonValidateRadio = view.findViewById<MaterialButton>(
            R.id.buttonValidateRadio
        )


        // =================================
        // 1. TOAST
        // =================================

        buttonToast.setOnClickListener {

            Toast.makeText(
                requireContext(),
                "Mensaje recibido desde el muro de boxes.",
                Toast.LENGTH_SHORT
            ).show()

            status.text =
                "Se mostró una notificación Toast."
        }


        // =================================
        // 2. SNACKBAR
        // =================================

        buttonSnackbar.setOnClickListener {

            status.text =
                "Instrucción enviada al piloto."

            Snackbar.make(
                view,
                "Instrucción enviada por radio.",
                Snackbar.LENGTH_LONG
            )
                .setAction("DESHACER") {

                    status.text =
                        "Se canceló la instrucción enviada."
                }
                .setActionTextColor(
                    resources.getColor(
                        R.color.team_mercedes,
                        requireContext().theme
                    )
                )
                .show()
        }


        // =================================
        // 3. ALERTDIALOG
        // =================================

        buttonDialog.setOnClickListener {

            AlertDialog.Builder(requireContext())

                .setTitle("Confirmar parada en boxes")

                .setMessage(
                    "¿Deseas solicitar una parada en boxes para el piloto?"
                )

                .setPositiveButton("Confirmar") {
                        _, _ ->

                    status.text =
                        "Parada en boxes confirmada."

                    Toast.makeText(
                        requireContext(),
                        "Solicitud confirmada.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                .setNegativeButton("Cancelar") {
                        dialog, _ ->

                    status.text =
                        "Solicitud de parada cancelada."

                    dialog.dismiss()
                }

                .show()
        }


        // =================================
        // 4. PROGRESSBAR HORIZONTAL
        // =================================

        buttonProgress.setOnClickListener {

            // Cancelar una animación anterior, si existe.

            progressAnimator?.cancel()

            progressTelemetry.progress = 0

            textPercentage.text =
                "Análisis de telemetría: 0 %"

            buttonProgress.isEnabled = false

            status.text =
                "Analizando datos de telemetría..."

            // Simulación de un progreso del 0 al 100 %.

            progressAnimator = ValueAnimator.ofInt(
                0,
                100
            ).apply {

                duration = 3000L

                addUpdateListener { animation ->

                    val progress =
                        animation.animatedValue as Int

                    progressTelemetry.progress =
                        progress

                    textPercentage.text =
                        "Análisis de telemetría: $progress %"
                }

                addListener(
                    object : AnimatorListenerAdapter() {

                        private var cancelled = false

                        override fun onAnimationCancel(
                            animation: Animator
                        ) {
                            cancelled = true
                        }

                        override fun onAnimationEnd(
                            animation: Animator
                        ) {

                            if (!cancelled && this@FeedbackFragment.view === view) {

                                buttonProgress.isEnabled = true

                                status.text =
                                    "Análisis de telemetría completado."
                            }
                        }
                    }
                )

                start()
            }
        }


        // =================================
        // 5. PROGRESSBAR CIRCULAR
        // =================================

        buttonCircular.setOnClickListener {

            buttonCircular.isEnabled = false

            progressCircular.visibility =
                View.VISIBLE

            status.text =
                "Sincronizando información del equipo..."

            val finishSynchronization = Runnable {

                if (this.view === view) {

                    progressCircular.visibility =
                        View.GONE

                    buttonCircular.isEnabled = true

                    status.text =
                        "Sincronización completada."
                }

                circularProgressRunnable = null

                circularProgressView = null
            }

            circularProgressRunnable =
                finishSynchronization

            circularProgressView = view

            view.postDelayed(
                finishSynchronization,
                2500L
            )
        }


        // =================================
        // 6. MENSAJE DE ERROR
        // =================================

        buttonValidateRadio.setOnClickListener {

            val code =
                radioInput.text.toString()
                    .trim()
                    .uppercase()

            when {

                code.isEmpty() -> {

                    radioLayout.error =
                        "Introduce un código de radio"

                    status.text =
                        "No se proporcionó un código."
                }

                code != "BOX" -> {

                    radioLayout.error =
                        "Código incorrecto. Prueba con BOX"

                    status.text =
                        "El código de radio no es válido."
                }

                else -> {

                    radioLayout.error = null

                    status.text =
                        "Código BOX validado correctamente."

                    Toast.makeText(
                        requireContext(),
                        "Código aceptado.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    // =================================
    // LIMPIEZA DEL FRAGMENT
    // =================================

    override fun onDestroyView() {

        // Detener la animación si el usuario
        // abandona la pantalla.

        progressAnimator?.cancel()

        progressAnimator = null

        // Cancelar la sincronización pendiente
        // para no actualizar una vista destruida.

        val pendingRunnable =
            circularProgressRunnable

        if (pendingRunnable != null) {

            circularProgressView?.removeCallbacks(
                pendingRunnable
            )
        }

        circularProgressRunnable = null

        circularProgressView = null

        super.onDestroyView()
    }
}