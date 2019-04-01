package com.github.gibbrich.albums.di.component

import com.github.gibbrich.albums.ui.viewmodel.AlbumsViewModel
import com.github.gibbrich.albums.di.module.AlbumsModule
import dagger.Subcomponent

@Subcomponent(modules = [
    AlbumsModule::class
])
interface AlbumsComponent {
    fun inject(entry: AlbumsViewModel)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AlbumsComponent
    }
}