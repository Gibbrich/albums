package com.github.gibbrich.albums.di

import android.app.Application

object DI {
    private lateinit var componentManager: ComponentManager

    fun init(context: Application) {
        componentManager = ComponentManager(context)
    }

    fun componentManager(): ComponentManager = componentManager
}