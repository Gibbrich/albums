package com.github.gibbrich.core.repository

import com.github.gibbrich.core.model.Album
import io.reactivex.Single

interface AlbumsRepository {
    fun getAlbums(isCacheDirty: Boolean = false): Single<List<Album>>

    fun getAlbum(albumId: Long): Single<Album>
}