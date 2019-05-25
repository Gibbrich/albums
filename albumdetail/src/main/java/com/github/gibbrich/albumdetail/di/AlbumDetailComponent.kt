package com.github.gibbrich.albumdetail.di

import com.github.gibbrich.albumdetail.ui.AlbumDetailActivity
import com.github.gibbrich.albumdetail.ui.AlbumViewModel
import com.github.gibbrich.data.di.DataComponent
import dagger.Component

@AlbumDetailScope
@Component(
    modules = [
        AlbumDetailModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface AlbumDetailComponent {
    fun inject(entry: AlbumViewModel)
    fun inject(entry: AlbumDetailActivity)
}