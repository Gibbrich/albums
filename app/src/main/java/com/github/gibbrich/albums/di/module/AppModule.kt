package com.github.gibbrich.albums.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.github.gibbrich.albums.AppDatabase
import dagger.Module
import dagger.Provides
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
}