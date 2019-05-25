package com.github.gibbrich.albums.data.network

import com.github.gibbrich.albums.data.network.model.NWAlbum
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("albums")
    fun getAlbums(): Single<List<NWAlbum>>

    @GET("albums/{albumId}")
    fun getAlbum(
        @Path("albumId") albumId: Long
    ): Single<NWAlbum>
}