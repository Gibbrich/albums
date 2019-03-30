package com.github.gibbrich.albums.di.component

import com.github.gibbrich.albums.MainActivity
import com.github.gibbrich.albums.di.module.AlbumsModule
import dagger.Subcomponent

@Subcomponent(modules = [
    AlbumsModule::class
])
interface AlbumsComponent {
    fun inject(entry: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AlbumsComponent
    }
}