package com.github.gibbrich.albums

import io.reactivex.Single

class AlbumsUseCase(
    private val albumsRepository: AlbumsRepository
) {
    fun getAlbums(): Single<List<Album>> = albumsRepository.getAlbums()
}