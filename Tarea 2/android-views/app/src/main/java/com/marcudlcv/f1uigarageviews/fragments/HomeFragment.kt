package com.marcudlcv.f1uigarageviews.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.google.android.material.card.MaterialCardView

import com.marcudlcv.f1uigarageviews.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        // =================================
        // 01 - FERRARI
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardTextInput,

            team = "FERRARI",

            title = "Registro de pilotos",

            description = "Entrada de texto",

            colorId = R.color.team_ferrari,

            destination = R.id.action_home_to_textInput
        )


        // =================================
        // 02 - MCLAREN
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardButtons,

            team = "MCLAREN",

            title = "Centro de control",

            description = "Botones y acciones",

            colorId = R.color.accent_mclaren,

            destination = R.id.action_home_to_buttons
        )


        // =================================
        // 03 - MERCEDES
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardSelection,

            team = "MERCEDES",

            title = "Configuración de carrera",

            description = "Elementos de selección",

            colorId = R.color.accent_mercedes,

            destination = R.id.action_home_to_selection
        )


        // =================================
        // 04 - WILLIAMS
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardLists,

            team = "WILLIAMS",

            title = "Parrilla de salida",

            description = "Listas y colecciones",

            colorId = R.color.team_williams,

            destination = R.id.action_home_to_lists
        )


        // =================================
        // 05 - ASTON MARTIN
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardFeedback,

            team = "ASTON MARTIN",

            title = "Información de carrera",

            description = "Información y retroalimentación",

            colorId = R.color.team_astonmartin,

            destination = R.id.action_home_to_feedback
        )


        // =================================
        // 06 - ALPINE
        // =================================

        setupCategory(
            view = view,

            cardId = R.id.cardLayouts,

            team = "ALPINE",

            title = "Diseño del paddock",

            description = "Contenedores y estructura",

            colorId = R.color.team_alpine,

            destination = R.id.action_home_to_layouts
        )
    }


    // =================================
    // CONFIGURACIÓN DE TARJETAS
    // =================================

    private fun setupCategory(
        view: View,
        cardId: Int,
        team: String,
        title: String,
        description: String,
        @ColorRes colorId: Int,
        destination: Int
    ) {

        // Obtener tarjeta

        val card =
            view.findViewById<MaterialCardView>(
                cardId
            )

        // Obtener elementos internos

        val teamText =
            card.findViewById<TextView>(
                R.id.categoryTeam
            )

        val titleText =
            card.findViewById<TextView>(
                R.id.categoryTitle
            )

        val descriptionText =
            card.findViewById<TextView>(
                R.id.categoryDescription
            )

        val accent =
            card.findViewById<View>(
                R.id.categoryAccent
            )

        // Obtener color de la escudería

        val color =
            ContextCompat.getColor(
                requireContext(),
                colorId
            )

        // Asignar información

        teamText.text = team

        titleText.text = title

        descriptionText.text = description

        // Aplicar color

        teamText.setTextColor(color)

        accent.setBackgroundColor(color)

        // Configurar navegación

        card.setOnClickListener {

            findNavController().navigate(
                destination
            )
        }
    }
}