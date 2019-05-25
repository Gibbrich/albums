package com.github.gibbrich.albums.ui.viewmodel

import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.github.gibbrich.albums.domain.usecase.AlbumFeature
import com.github.gibbrich.albums.ui.activity.AlbumDetailView

class AlbumBinding(
    private val albumFeature: AlbumFeature
) {
    private val binder = Binder()

    fun setup(view: AlbumDetailView) {
        binder.bind(albumFeature to view.viewModelConsumer using this::convert)
        binder.bind(view.uiEventsSource to albumFeature using this::convert)
        binder.bind(albumFeature.news to view.newsConsumer using this::convert)
    }

    private fun convert(state: AlbumFeature.State) = AlbumDetailView.AlbumViewModelNew(
        state.isLoading,
        state.albumTitle
    )

    private fun convert(event: AlbumDetailView.UIEvents) = when (event) {
        is AlbumDetailView.UIEvents.LoadAlbumSwiped -> AlbumFeature.Wish.LoadAlbum(event.albumId)
    }

    private fun convert(news: AlbumFeature.News): AlbumDetailView.News = when (news) {
        is AlbumFeature.News.ErrorExecutingRequest -> AlbumDetailView.News.LoadFailed
    }
}