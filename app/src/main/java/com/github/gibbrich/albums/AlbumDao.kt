package com.github.gibbrich.albums

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Query("SELECT id, user_id, title FROM Album ORDER BY title")
    fun getOrders(): Single<List<DBAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albums: List<DBAlbum>)
}