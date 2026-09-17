package com.marcudlcv.dolist

import android.app.Application
import com.marcudlcv.dolist.data.AppContainer

class DoListApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(this)
    }
}