package com.github.gibbrich.albums.di

import com.github.gibbrich.albums.ui.AlbumsViewModel
import com.github.gibbrich.data.di.DataComponent
import dagger.Component

@AlbumsScope
@Component(
    modules = [
        AlbumsModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface AlbumsComponent {
    fun inject(entry: AlbumsViewModel)
}