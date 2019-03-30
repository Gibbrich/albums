package com.github.gibbrich.albums

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.gibbrich.albums.di.DI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var albumsUseCase: AlbumsUseCase

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DI.componentManager().albumsComponent().inject(this)

        activity_main_albums_list.layoutManager = LinearLayoutManager(this)

        safeSubscribe {
            albumsUseCase
                .getAlbums()
                .schedulersIoToMain()
                .subscribe(this::handle, this::handleEror)
        }
    }

    private fun handle(albums: List<Album>) {
        activity_main_albums_list.adapter = AlbumsAdapter(albums)
    }

    private fun handleEror(err: Throwable) {
        Toast.makeText(this, err.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    fun safeSubscribe(action: () -> Disposable) {
        compositeDisposable.add(action())
    }

    fun dispose(){
        compositeDisposable.dispose()
        compositeDisposable = CompositeDisposable()
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
