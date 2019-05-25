package com.github.gibbrich.albums.di

import com.github.gibbrich.albums.domain.AlbumsUseCase
import com.github.gibbrich.core.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AlbumsModule {

    @Provides
    @Reusable
    fun providesAlbumsUseCase(repository: AlbumsRepository): AlbumsUseCase {
        return AlbumsUseCase(repository)
    }
}