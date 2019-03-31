package com.github.gibbrich.albums

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [
    DBAlbum::class
], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}