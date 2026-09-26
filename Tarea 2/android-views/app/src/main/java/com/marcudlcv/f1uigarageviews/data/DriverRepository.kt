package com.marcudlcv.f1uigarageviews.data

object DriverRepository {

    private val drivers = mutableListOf<Driver>()

    fun addDriver(driver: Driver): Boolean {

        val numberExists = drivers.any {
            it.number == driver.number
        }

        if (numberExists) {
            return false
        }

        drivers.add(driver)

        return true
    }

    fun getDrivers(): List<Driver> {
        return drivers.toList()
    }

    fun getDriverCount(): Int {
        return drivers.size
    }

    fun clearDrivers() {
        drivers.clear()
    }
}