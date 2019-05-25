package com.github.gibbrich.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.github.gibbrich.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DBModule(private val context: Context) {
    @Provides
    @DataScope
    fun provideDB(): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "albums_db")
            .build()
    }
}