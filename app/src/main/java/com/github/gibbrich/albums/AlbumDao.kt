package com.github.gibbrich.albums

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Query("SELECT id, user_id, title FROM Album ORDER BY title")
    fun getOrders(): Single<List<DBAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albums: List<DBAlbum>)
}