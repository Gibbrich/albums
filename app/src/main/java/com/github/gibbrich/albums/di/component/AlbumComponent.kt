package com.github.gibbrich.albums.di.component

import com.github.gibbrich.albums.di.module.AlbumDetailModule
import com.github.gibbrich.albums.ui.activity.AlbumDetailActivity
import com.github.gibbrich.albums.ui.viewmodel.AlbumViewModel
import dagger.Subcomponent

@Subcomponent(modules = [
    AlbumDetailModule::class
])
interface AlbumComponent {
    fun inject(entry: AlbumViewModel)
    fun inject(entry: AlbumDetailActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AlbumComponent
    }
}