package com.github.gibbrich.albums

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    DBAlbum::class
], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}