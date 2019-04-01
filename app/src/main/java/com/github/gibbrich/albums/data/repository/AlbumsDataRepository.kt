package com.github.gibbrich.albums.data.repository

import com.github.gibbrich.albums.domain.model.Album
import com.github.gibbrich.albums.domain.repository.AlbumsRepository
import com.github.gibbrich.albums.data.converter.AlbumConverter
import com.github.gibbrich.albums.data.network.Api
import com.github.gibbrich.albums.data.db.AppDatabase
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

        else -> {
            getAndCacheLocalAlbums()
        }
    }

    private fun getAndCacheLocalAlbums(): Single<List<Album>> =
        db.albumDao()
            .getOrders()
            .map { it.map(AlbumConverter::fromDB) }
            .flatMap {
                if (it.isEmpty()) {
                    getAndSaveRemoteAlbums()
                } else {
                    Single.just(it)
                        .doOnSuccess(this::refreshCache)
                }
            }

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