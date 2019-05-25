package com.github.gibbrich.albums.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.gibbrich.albums.di.DI
import com.github.gibbrich.albums.domain.model.Album
import com.github.gibbrich.albums.domain.usecase.AlbumsUseCase
import com.github.gibbrich.albums.domain.utils.schedulersIoToMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AlbumsViewModel: ViewModel() {
    val state = MutableLiveData<State>()
    val action = MutableLiveData<Action>()
    private var disposables = CompositeDisposable()

    @Inject
    internal lateinit var albumsUseCase: AlbumsUseCase

    init {
        DI.componentManager().albumsComponent().inject(this)
        state.value = State.Empty
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    fun safeSubscribe(action: () -> Disposable) {
        disposables.add(action())
    }

    fun dispose(){
        disposables.dispose()
        disposables = CompositeDisposable()
    }

    fun fetchAlbums() {
        safeSubscribe {
            albumsUseCase
                .getAlbums()
                .schedulersIoToMain()
                .doOnSubscribe { state.value = State.Loading }
                .subscribe(this::handle, this::handleEror)
        }
    }

    fun onAlbumClicked(albumId: Long) {
        action.value = Action.SwitchToAlbumDetailScreen(albumId)
    }

    private fun handle(albums: List<Album>) {
        state.value = State.Loaded(albums)
    }

    private fun handleEror(err: Throwable) {
        state.value = State.LoadError
    }

    sealed class State {
        object LoadError: State()
        object Loading: State()
        object Empty: State()
        data class Loaded(val albums: List<Album>): State()
    }

    sealed class Action {
        data class SwitchToAlbumDetailScreen(val albumId: Long): Action()
    }
}