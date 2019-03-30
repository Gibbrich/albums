package com.github.gibbrich.albums

import io.reactivex.Single

class AlbumsDataRepository(
    private val db: AppDatabase,
    private val api: Api
) : AlbumsRepository {
    private val cachedAlbums: MutableList<Album> = mutableListOf()

    override fun getAlbums(isCacheDirty: Boolean): Single<List<Album>> = when {
        cachedAlbums.isNotEmpty() and isCacheDirty.not() -> {
            Single.just(cachedAlbums)
        }

        isCacheDirty -> {
            getAndSaveRemoteAlbums()
        }

        else -> {
            getAndCacheLocalAlbums()
        }
    }

    private fun getAndCacheLocalAlbums(): Single<List<Album>> =
        db.albumDao()
            .getOrders()
            .map { it.map(AlbumConverter::fromDB) }
            .doOnSuccess(this::refreshCache)

    private fun getAndSaveRemoteAlbums(): Single<List<Album>> =
        api.getAlbums()
            .map { it.map(AlbumConverter::fromNetwork).sortedBy(Album::title) }
            .doOnSuccess {
                refreshCache(it)
                it.map(AlbumConverter::toDB).also(db.albumDao()::insert)
            }

    private fun refreshCache(albums: List<Album>) {
        cachedAlbums.clear()
        cachedAlbums.addAll(albums)
    }
}