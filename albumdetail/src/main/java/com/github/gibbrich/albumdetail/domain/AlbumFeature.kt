package com.github.gibbrich.albumdetail.domain

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.github.gibbrich.core.repository.AlbumsRepository
import com.github.gibbrich.core.utils.schedulersIoToMain
import io.reactivex.Observable

class AlbumFeature(
    albumsRepository: AlbumsRepository
) : ActorReducerFeature<AlbumFeature.Wish, AlbumFeature.Effect, AlbumFeature.State, AlbumFeature.News>(
    initialState = State(),
    actor = ActorImpl(albumsRepository),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {
    data class State(
        val isLoading: Boolean = false,
        val albumTitle: String? = null
    )

    sealed class Wish {
        data class LoadAlbum(val albumId: Long) : Wish()
    }

    sealed class Effect {
        object StartedLoading : Effect()
        data class LoadSuccess(val payload: String) : Effect()
        data class LoadError(val throwable: Throwable) : Effect()
    }

    class ActorImpl(
        private val albumsRepository: AlbumsRepository
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, action: Wish): Observable<out Effect> = when (action) {
            is Wish.LoadAlbum -> {
                if (state.isLoading.not()) {
                    albumsRepository
                        .getAlbum(action.albumId)
                        .toObservable()
                        .schedulersIoToMain()
                        .map<Effect> {
                            Effect.LoadSuccess(
                                it.title
                            )
                        }
                        .startWith(Effect.StartedLoading)
                        .onErrorReturn { Effect.LoadError(it) }
                } else {
                    Observable.empty()
                }
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            Effect.StartedLoading -> state.copy(isLoading = true)
            is Effect.LoadSuccess -> state.copy(isLoading = false, albumTitle = effect.payload)
            is Effect.LoadError -> state.copy(isLoading = false)
        }
    }

    sealed class News {
        data class ErrorExecutingRequest(val throwable: Throwable) : News()
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(action: Wish, effect: Effect, state: State): News? = when (effect) {
            is Effect.LoadError -> News.ErrorExecutingRequest(
                effect.throwable
            )
            else -> null
        }
    }
}