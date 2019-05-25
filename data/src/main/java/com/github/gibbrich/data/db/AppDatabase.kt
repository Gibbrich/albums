package com.github.gibbrich.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.gibbrich.data.db.dao.AlbumDao
import com.github.gibbrich.data.db.model.DBAlbum


@Database(entities = [
    DBAlbum::class
], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}