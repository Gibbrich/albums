package com.github.gibbrich.albumdetail.di

import com.github.gibbrich.core.repository.AlbumsRepository
import com.github.gibbrich.albumdetail.domain.AlbumFeature
import com.github.gibbrich.albumdetail.domain.AlbumUseCase
import com.github.gibbrich.albumdetail.ui.AlbumBinding
import dagger.Module
import dagger.Provides

@Module
class AlbumDetailModule {

    @Provides
    fun provideAlbumDetailUseCase(
        albumsRepository: AlbumsRepository
    ): AlbumUseCase = AlbumUseCase(albumsRepository)

    @Provides
    fun provideAlbumDetailBinder(
        albumFeature: AlbumFeature
    ): AlbumBinding = AlbumBinding(albumFeature)

    @Provides
    @AlbumDetailScope
    fun provideAlbumFeature(
        albumsRepository: AlbumsRepository
    ): AlbumFeature = AlbumFeature(albumsRepository)
}