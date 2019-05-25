package com.github.gibbrich.albumdetail.domain

import com.github.gibbrich.core.repository.AlbumsRepository

class AlbumUseCase(private val albumsRepository: AlbumsRepository) {
    fun getAlbum(albumId: Long) = albumsRepository.getAlbum(albumId)
}