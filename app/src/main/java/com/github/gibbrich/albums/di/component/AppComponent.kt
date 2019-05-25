package com.github.gibbrich.albums.di.component

import com.github.gibbrich.albums.di.module.AlbumsModule
import com.github.gibbrich.albums.di.module.ApiModule
import com.github.gibbrich.albums.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    AlbumsModule::class
])
interface AppComponent {
    fun albumsBuilder(): AlbumsComponent.Builder
    fun albumBuilder(): AlbumComponent.Builder
}