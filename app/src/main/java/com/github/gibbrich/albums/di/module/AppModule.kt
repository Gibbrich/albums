package com.github.gibbrich.albums.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.github.gibbrich.albums.data.db.AppDatabase
import com.github.gibbrich.albums.data.network.Api
import com.github.gibbrich.albums.data.repository.AlbumsDataRepository
import com.github.gibbrich.albums.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDB(): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "albums_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideAlbumsRepository(
        api: Api,
        db: AppDatabase
    ): AlbumsRepository {
        return AlbumsDataRepository(db, api)
    }
}