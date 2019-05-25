package com.github.gibbrich.albums.di

import com.github.gibbrich.data.di.DI

object DI {
    val albumsComponent: AlbumsComponent by lazy {
        DaggerAlbumsComponent
            .builder()
            .dataComponent(DI.dataComponent)
            .build()
    }
}