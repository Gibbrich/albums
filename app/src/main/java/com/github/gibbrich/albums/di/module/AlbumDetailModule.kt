package com.github.gibbrich.albums.di.module

import com.github.gibbrich.albums.domain.repository.AlbumsRepository
import com.github.gibbrich.albums.domain.usecase.AlbumFeature
import com.github.gibbrich.albums.domain.usecase.AlbumUseCase
import com.github.gibbrich.albums.ui.viewmodel.AlbumBinding
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
}