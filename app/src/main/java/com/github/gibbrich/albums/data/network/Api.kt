package com.github.gibbrich.albums.data.network

import com.github.gibbrich.albums.data.network.model.NWAlbum
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("albums")
    fun getAlbums(): Single<List<NWAlbum>>
}