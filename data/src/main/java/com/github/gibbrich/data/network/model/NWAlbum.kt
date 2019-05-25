package com.github.gibbrich.data.network.model

import com.google.gson.annotations.SerializedName

data class NWAlbum(
    @SerializedName("userId")
    val id: Long? = null,

    @SerializedName("id")
    val userId: Long? = null,

    @SerializedName("title")
    val title: String? = null
)