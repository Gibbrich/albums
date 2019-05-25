package com.github.gibbrich.albums.domain.repository

import com.github.gibbrich.albums.domain.model.Album
import io.reactivex.Single

interface AlbumsRepository {
    fun getAlbums(isCacheDirty: Boolean = false): Single<List<Album>>

    fun getAlbum(albumId: Long): Single<Album>
}