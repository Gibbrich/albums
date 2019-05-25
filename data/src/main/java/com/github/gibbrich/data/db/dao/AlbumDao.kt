package com.github.gibbrich.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.gibbrich.data.db.model.DBAlbum
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Query("SELECT id, user_id, title FROM Album ORDER BY title")
    fun getOrders(): Single<List<DBAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albums: List<DBAlbum>)
}