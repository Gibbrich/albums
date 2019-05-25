package com.github.gibbrich.albumdetail.ui

import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using

class AlbumBinding(
    private val albumFeature: com.github.gibbrich.albumdetail.domain.AlbumFeature
) {
    private val binder = Binder()

    fun setup(view: AlbumDetailView) {
        binder.bind(albumFeature to view.viewModelConsumer using this::convert)
        binder.bind(view.uiEventsSource to albumFeature using this::convert)
        binder.bind(albumFeature.news to view.newsConsumer using this::convert)
    }

    private fun convert(state: com.github.gibbrich.albumdetail.domain.AlbumFeature.State) = AlbumDetailView.AlbumViewModelNew(
        state.isLoading,
        state.albumTitle
    )

    private fun convert(event: AlbumDetailView.UIEvents) = when (event) {
        is AlbumDetailView.UIEvents.LoadAlbumSwiped -> com.github.gibbrich.albumdetail.domain.AlbumFeature.Wish.LoadAlbum(event.albumId)
    }

    private fun convert(news: com.github.gibbrich.albumdetail.domain.AlbumFeature.News): AlbumDetailView.News = when (news) {
        is com.github.gibbrich.albumdetail.domain.AlbumFeature.News.ErrorExecutingRequest -> AlbumDetailView.News.LoadFailed
    }
}