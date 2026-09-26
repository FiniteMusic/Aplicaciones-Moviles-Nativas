package com.marcudlcv.f1uigarageviews.fragments


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.card.MaterialCardView

import com.marcudlcv.f1uigarageviews.R
import com.marcudlcv.f1uigarageviews.data.Driver
import com.marcudlcv.f1uigarageviews.data.DriverAdapter
import com.marcudlcv.f1uigarageviews.data.DriverRepository

class ListsFragment : Fragment(R.layout.fragment_lists) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // =================================
        // REFERENCIAS
        // =================================

        val listView = view.findViewById<ListView>(
            R.id.listViewDrivers
        )

        val recyclerView = view.findViewById<RecyclerView>(
            R.id.recyclerViewDrivers
        )

        val gridView = view.findViewById<GridView>(
            R.id.gridViewTeams
        )

        val driverCount = view.findViewById<TextView>(
            R.id.textDriverCount
        )

        val selection = view.findViewById<TextView>(
            R.id.textListSelection
        )

        val emptyCard = view.findViewById<MaterialCardView>(
            R.id.cardEmptyDrivers
        )


        // =================================
        // OBTENER PILOTOS REGISTRADOS
        // =================================

        val drivers = DriverRepository.getDrivers()

        driverCount.text = drivers.size.toString()

        emptyCard.visibility = if (drivers.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }


        // =================================
        // 1. LISTVIEW
        // =================================

        val driverNames = drivers.map { driver ->

            "#${driver.number} · ${driver.name} · ${driver.team}"
        }

        val listAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            driverNames
        )

        listView.adapter = listAdapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener {
                    _, _, position, _ ->

                val driver = drivers[position]

                showDriverInformation(
                    selection,
                    driver
                )
            }

        // Ajustar la altura de ListView para el ScrollView.

        listView.post {

            val itemHeight = 56

            val density = resources.displayMetrics.density

            val totalHeight = (
                    itemHeight * density * drivers.size
                    ).toInt()

            listView.layoutParams =
                listView.layoutParams.apply {

                    height = totalHeight
                }
        }


        // =================================
        // 2. RECYCLERVIEW
        // =================================

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerView.isNestedScrollingEnabled = false

        recyclerView.adapter = DriverAdapter(
            drivers
        ) { driver ->

            showDriverInformation(
                selection,
                driver
            )
        }


        // =================================
        // 3. GRIDVIEW
        // =================================

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

        val teamAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            teams
        )

        gridView.adapter = teamAdapter

        gridView.onItemClickListener =
            AdapterView.OnItemClickListener {
                    _, _, position, _ ->

                val selectedTeam = teams[position]

                val teamDrivers = drivers.count {
                    it.team == selectedTeam
                }

                selection.text = """
                    Escudería: $selectedTeam
                    
                    Pilotos registrados: $teamDrivers
                """.trimIndent()
            }

        // Ajustar la altura del GridView.

        gridView.post {

            val rows = (teams.size + 1) / 2

            val rowHeight = 56

            val density = resources.displayMetrics.density

            val totalHeight = (
                    rows * rowHeight * density
                    ).toInt() + gridView.paddingTop +
                    gridView.paddingBottom

            gridView.layoutParams =
                gridView.layoutParams.apply {

                    height = totalHeight
                }
        }
    }


    // =================================
    // MOSTRAR INFORMACIÓN DEL PILOTO
    // =================================

    private fun showDriverInformation(
        selection: TextView,
        driver: Driver
    ) {

        selection.text = """
            Piloto: ${driver.name}
            
            Número: ${driver.number}
            
            Escudería: ${driver.team}
        """.trimIndent()
    }
}