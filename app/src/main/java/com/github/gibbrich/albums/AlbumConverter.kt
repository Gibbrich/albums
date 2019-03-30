package com.github.gibbrich.albums

object AlbumConverter {
    fun fromDB(data: DBAlbum): Album = Album(data.id, data.userId, data.title)

    fun fromNetwork(data: NWAlbum): Album {
        val id = getOrDie(data.id, "id")
        val userId = getOrDie(data.userId, "userId")
        val title = getOrDie(data.title, "title")

        return Album(id, userId, title)
    }

    fun toDB(data: Album): DBAlbum = DBAlbum(data.id, data.userId, data.title)
}