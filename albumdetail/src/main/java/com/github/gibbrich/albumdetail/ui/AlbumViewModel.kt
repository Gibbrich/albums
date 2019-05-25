package com.github.gibbrich.albumdetail.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.gibbrich.albumdetail.di.DI
import com.github.gibbrich.albumdetail.domain.AlbumUseCase
import com.github.gibbrich.core.model.Album
import com.github.gibbrich.core.utils.schedulersIoToMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AlbumViewModel: ViewModel() {

    @Inject
    internal lateinit var albumUseCase: AlbumUseCase

    val album = MutableLiveData<Album>()

    val state = MutableLiveData<AlbumDetailState>()

    private var disposables = CompositeDisposable()

    init {
        DI.albumDetailComponent.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    private fun safeSubscribe(action: () -> Disposable) {
        disposables.add(action())
    }

    private fun dispose(){
        disposables.dispose()
        disposables = CompositeDisposable()
    }

    fun getAlbum(albumId: Long) {
        safeSubscribe {
            albumUseCase
                .getAlbum(albumId)
                .schedulersIoToMain()
                .subscribe({
                    state.value = AlbumDetailState.ShowAlbum(it)
                }, {
                    state.value = AlbumDetailState.ShowError
                })
        }
    }

    sealed class AlbumDetailState {
        data class ShowAlbum(val album: Album): AlbumDetailState()
        object HideRefreshing: AlbumDetailState()
        object ShowError: AlbumDetailState()
    }
}