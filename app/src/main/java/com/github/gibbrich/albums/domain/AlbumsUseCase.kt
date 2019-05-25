package com.github.gibbrich.albums.domain

import com.github.gibbrich.core.model.Album
import com.github.gibbrich.core.repository.AlbumsRepository
import io.reactivex.Single

class AlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {
    fun getAlbums(): Single<List<Album>> = albumsRepository.getAlbums()
}