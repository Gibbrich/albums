package com.github.gibbrich.albums

import io.reactivex.Single

interface AlbumsRepository {
    fun getAlbums(isCacheDirty: Boolean = false): Single<List<Album>>
}