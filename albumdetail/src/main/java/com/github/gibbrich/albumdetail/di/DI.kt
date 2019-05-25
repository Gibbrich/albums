package com.github.gibbrich.albumdetail.di

import com.github.gibbrich.data.di.DI

object DI {
    val albumDetailComponent: AlbumDetailComponent by lazy {
        DaggerAlbumDetailComponent
            .builder()
            .dataComponent(DI.dataComponent)
            .build()
    }
}