package com.github.gibbrich.data.di

import com.github.gibbrich.core.repository.AlbumsRepository
import com.github.gibbrich.data.db.AppDatabase
import com.github.gibbrich.data.network.Api
import com.github.gibbrich.data.repository.AlbumsDataRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideAlbumsRepository(
        api: Api,
        db: AppDatabase
    ): AlbumsRepository {
        return AlbumsDataRepository(db, api)
    }
}