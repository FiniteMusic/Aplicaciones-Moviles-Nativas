package com.marcudlcv.f1uigaragecompose.data

import androidx.compose.runtime.mutableStateListOf

object DriverRepository {

    private val _drivers = mutableStateListOf<Driver>()

    val drivers: List<Driver>
        get() = _drivers

    fun addDriver(driver: Driver): Boolean {

        val duplicatedNumber = _drivers.any {
            it.number == driver.number
        }

        if (duplicatedNumber) {
            return false
        }

        _drivers.add(driver)

        return true
    }

    fun removeDriver(driver: Driver) {
        _drivers.remove(driver)
    }

    fun clear() {
        _drivers.clear()
    }
}