package com.github.gibbrich.data.di

import android.app.Application

object DI {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val dataComponent: DataComponent by lazy {
        DaggerDataComponent
            .builder()
            .dBModule(DBModule(application))
            .build()
    }
}