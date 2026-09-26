package com.marcudlcv.f1uigarageviews.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton

import com.marcudlcv.f1uigarageviews.R

class LayoutsFragment : Fragment(R.layout.fragment_layouts) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // =================================
        // REFERENCIAS
        // =================================

        val linearDemo = view.findViewById<LinearLayout>(
            R.id.linearDemo
        )

        val buttonOrientation = view.findViewById<MaterialButton>(
            R.id.buttonChangeOrientation
        )

        val frameBadge = view.findViewById<TextView>(
            R.id.textFrameBadge
        )

        val buttonToggleBadge = view.findViewById<MaterialButton>(
            R.id.buttonToggleBadge
        )

        val status = view.findViewById<TextView>(
            R.id.textLayoutStatus
        )


        // =================================
        // 1. CAMBIAR ORIENTACIÓN
        // =================================

        buttonOrientation.setOnClickListener {

            if (
                linearDemo.orientation ==
                LinearLayout.VERTICAL
            ) {

                // Cambiar el contenedor a horizontal.

                linearDemo.orientation =
                    LinearLayout.HORIZONTAL

                // Distribuir los tres sectores
                // con el mismo ancho.

                for (index in 0 until linearDemo.childCount) {

                    val child =
                        linearDemo.getChildAt(index)

                    val params =
                        child.layoutParams as LinearLayout.LayoutParams

                    params.width = 0

                    params.height = dpToPx(64)

                    params.weight = 1f

                    params.topMargin = 0

                    params.marginStart =
                        if (index == 0) 0 else dpToPx(4)

                    params.marginEnd =
                        if (index == linearDemo.childCount - 1) {
                            0
                        } else {
                            dpToPx(4)
                        }

                    child.layoutParams = params
                }

                buttonOrientation.text =
                    "Cambiar a vertical"

                status.text =
                    "LinearLayout: orientación horizontal."

            } else {

                // Regresar a la orientación vertical.

                linearDemo.orientation =
                    LinearLayout.VERTICAL

                for (index in 0 until linearDemo.childCount) {

                    val child =
                        linearDemo.getChildAt(index)

                    val params =
                        child.layoutParams as LinearLayout.LayoutParams

                    params.width =
                        LinearLayout.LayoutParams.MATCH_PARENT

                    params.height =
                        dpToPx(48)

                    params.weight = 0f

                    params.topMargin =
                        if (index == 0) 0 else dpToPx(8)

                    params.marginStart = 0

                    params.marginEnd = 0

                    child.layoutParams = params
                }

                buttonOrientation.text =
                    "Cambiar a horizontal"

                status.text =
                    "LinearLayout: orientación vertical."
            }
        }


        // =================================
        // 2. MOSTRAR / OCULTAR INDICADOR
        // =================================

        buttonToggleBadge.setOnClickListener {

            if (frameBadge.visibility == View.VISIBLE) {

                frameBadge.visibility =
                    View.GONE

                buttonToggleBadge.text =
                    "Mostrar indicador"

                status.text =
                    "FrameLayout: indicador oculto."

            } else {

                frameBadge.visibility =
                    View.VISIBLE

                buttonToggleBadge.text =
                    "Ocultar indicador"

                status.text =
                    "FrameLayout: indicador visible."
            }
        }
    }


    // =================================
    // CONVERSIÓN DE DP A PÍXELES
    // =================================

    private fun dpToPx(dp: Int): Int {

        val density =
            resources.displayMetrics.density

        return (dp * density).toInt()
    }
}