package com.github.gibbrich.albums.domain.usecase

import com.github.gibbrich.albums.domain.model.Album
import com.github.gibbrich.albums.domain.repository.AlbumsRepository
import io.reactivex.Single

class AlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {
    fun getAlbums(): Single<List<Album>> = albumsRepository.getAlbums()
}