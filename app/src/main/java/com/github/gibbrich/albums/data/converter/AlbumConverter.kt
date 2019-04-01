package com.github.gibbrich.albums.data.converter

import com.github.gibbrich.albums.domain.model.Album
import com.github.gibbrich.albums.data.db.model.DBAlbum
import com.github.gibbrich.albums.data.utils.getOrDie
import com.github.gibbrich.albums.data.network.model.NWAlbum

object AlbumConverter {
    fun fromDB(data: DBAlbum): Album =
        Album(data.id, data.userId, data.title)

    fun fromNetwork(data: NWAlbum): Album {
        val id = getOrDie(data.id, "id")
        val userId = getOrDie(data.userId, "userId")
        val title = getOrDie(data.title, "title")

        return Album(id, userId, title)
    }

    fun toDB(data: Album): DBAlbum =
        DBAlbum(data.id, data.userId, data.title)
}