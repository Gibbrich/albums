package com.github.gibbrich.albums

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

@Entity(tableName = "Album", primaryKeys = ["id", "user_id"])
data class DBAlbum(
    val id: Long,

    @ColumnInfo(name = "user_id")
    val userId: Long,

    val title: String
)