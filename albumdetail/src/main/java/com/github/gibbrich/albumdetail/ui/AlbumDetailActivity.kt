package com.github.gibbrich.albumdetail.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.gibbrich.albumdetail.R
import com.github.gibbrich.albumdetail.di.DI
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_album_detail.*
import java.lang.Exception
import javax.inject.Inject

class AlbumDetailActivity : AppCompatActivity(), AlbumDetailView {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"
        fun getIntent(context: Context, albumId: Long) = Intent(context, AlbumDetailActivity::class.java).apply {
            putExtra(EXTRA_ALBUM_ID, albumId)
        }

    }

    override val newsConsumer = Consumer(this::handleNews)
    override val viewModelConsumer = Consumer(this::handleViewModel)
    override val uiEventsSource: BehaviorSubject<AlbumDetailView.UIEvents> = BehaviorSubject.create()

    @Inject
    lateinit var binding: AlbumBinding

    private val albumIdParam by lazy {
        intent?.extras?.getLong(EXTRA_ALBUM_ID) ?: throw Exception("Should pass albumId as extra")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        DI.albumDetailComponent.inject(this)
        binding.setup(this)

        activity_album_swipe_refresh_layout.setOnRefreshListener {
            uiEventsSource.onNext(AlbumDetailView.UIEvents.LoadAlbumSwiped(albumIdParam))
        }

    }

    private fun handleNews(news: AlbumDetailView.News) = when (news) {
        AlbumDetailView.News.LoadFailed -> {
            Toast.makeText(this, "Load failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleViewModel(viewModel: AlbumDetailView.AlbumViewModelNew) {
        activity_album_swipe_refresh_layout.isRefreshing = viewModel.isLoading

        viewModel.albumTitle
            ?.let {
                activity_album_detail_title.text = it
                activity_album_detail_title.visibility = View.VISIBLE
            }
            ?: kotlin.run {
                activity_album_detail_title.visibility = View.GONE
            }
    }
}

interface AlbumDetailView {
    val uiEventsSource: Observable<AlbumDetailView.UIEvents>
    val newsConsumer: Consumer<AlbumDetailView.News>
    val viewModelConsumer: Consumer<AlbumViewModelNew>

    sealed class UIEvents {
        data class LoadAlbumSwiped(val albumId: Long) : UIEvents()
    }

    sealed class News {
        object LoadFailed : News()
    }

    data class AlbumViewModelNew(
        val isLoading: Boolean,
        val albumTitle: String?
    )
}