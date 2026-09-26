package com.marcudlcv.f1uigarageviews.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.marcudlcv.f1uigarageviews.R

class DriverAdapter(
    private val drivers: List<Driver>,
    private val onDriverClick: (Driver) -> Unit
) : RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {

    class DriverViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val number: TextView = itemView.findViewById(
            R.id.textDriverNumber
        )

        val name: TextView = itemView.findViewById(
            R.id.textDriverName
        )

        val team: TextView = itemView.findViewById(
            R.id.textDriverTeam
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DriverViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_driver,
                parent,
                false
            )

        return DriverViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DriverViewHolder,
        position: Int
    ) {

        val driver = drivers[position]

        holder.number.text =
            driver.number.toString().padStart(2, '0')

        holder.name.text =
            driver.name

        holder.team.text =
            driver.team

        holder.itemView.setOnClickListener {

            onDriverClick(driver)
        }
    }

    override fun getItemCount(): Int {
        return drivers.size
    }
}