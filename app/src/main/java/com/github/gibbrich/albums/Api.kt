package com.github.gibbrich.albums

import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("albums")
    fun getAlbums(): Single<List<NWAlbum>>
}