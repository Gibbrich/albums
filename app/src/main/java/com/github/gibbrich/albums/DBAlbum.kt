package com.github.gibbrich.albums

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
data class DBAlbum(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "user_id")
    val userId: Long,

    val title: String
)