package com.github.gibbrich.albums.di

import android.app.Application
import com.github.gibbrich.albums.di.component.AlbumsComponent
import com.github.gibbrich.albums.di.component.AppComponent
import com.github.gibbrich.albums.di.component.DaggerAppComponent
import com.github.gibbrich.albums.di.module.AppModule

class ComponentManager(private val context: Application) {
    private val internalAppComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(context))
            .build()
    }

    private val internalAlbumsComponent: AlbumsComponent by lazy {
        internalAppComponent
            .albumsBuilder()
            .build()
    }

    fun albumsComponent(): AlbumsComponent = internalAlbumsComponent
}