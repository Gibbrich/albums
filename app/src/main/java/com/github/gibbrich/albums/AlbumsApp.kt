package com.github.gibbrich.albums

import android.app.Application
import com.github.gibbrich.albums.di.DI

class AlbumsApp: Application() {
    override fun onCreate() {
        super.onCreate()

        DI.init(this)
    }
}