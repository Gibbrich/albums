package com.github.gibbrich.albums.domain.usecase

import com.github.gibbrich.albums.domain.repository.AlbumsRepository

class AlbumUseCase(private val albumsRepository: AlbumsRepository) {
    fun getAlbum(albumId: Long) = albumsRepository.getAlbum(albumId)
}