package com.github.gibbrich.albums.di.module

import com.github.gibbrich.albums.di.component.AlbumComponent
import com.github.gibbrich.albums.domain.repository.AlbumsRepository
import com.github.gibbrich.albums.domain.usecase.AlbumFeature
import com.github.gibbrich.albums.domain.usecase.AlbumsUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module(subcomponents = [
    AlbumComponent::class
])
class AlbumsModule {

    @Provides
    @Reusable
    fun providesAlbumsUseCase(repository: AlbumsRepository): AlbumsUseCase {
        return AlbumsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAlbumFeature(repository: AlbumsRepository): AlbumFeature {
        return AlbumFeature(repository)
    }
}