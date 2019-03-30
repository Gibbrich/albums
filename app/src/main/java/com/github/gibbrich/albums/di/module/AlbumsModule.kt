package com.github.gibbrich.albums.di.module

import com.github.gibbrich.albums.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AlbumsModule {

    @Provides
    @Reusable
    fun provideAlbumsRepository(
        api: Api,
        db: AppDatabase
    ): AlbumsRepository {
        return AlbumsDataRepository(db, api)
    }

    @Provides
    @Reusable
    fun providesAlbumsUseCase(repository: AlbumsRepository): AlbumsUseCase {
        return AlbumsUseCase(repository)
    }
}