package com.marcudlcv.f1uigarageviews

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Obtener la barra superior

        val toolbar = findViewById<MaterialToolbar>(
            R.id.topAppBar
        )

        setSupportActionBar(toolbar)

        // Obtener el NavHostFragment

        navHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
            ) as NavHostFragment

        val navController =
            navHostFragment.navController

        // Configurar la pantalla principal

        appBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.homeFragment)
            )

        // Conectar Toolbar con Navigation Component

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController =
            navHostFragment.navController

        return navController.navigateUp(
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}