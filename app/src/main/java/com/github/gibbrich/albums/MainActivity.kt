package com.github.gibbrich.albums

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
        model.state.observe(this, Observer { handleState(it!!) })

        activity_main_swipe_layout.setOnRefreshListener(model::fetchAlbums)

        activity_main_albums_list.layoutManager = LinearLayoutManager(this)
        adapter = AlbumsAdapter()
        activity_main_albums_list.adapter = adapter
    }

    private fun handleState(state: AlbumsViewModel.State): Unit = when (state) {
        AlbumsViewModel.State.LoadError -> {
            activity_main_empty_label.text = getString(R.string.activity_main_error_occured)
            activity_main_empty_label.visibility = View.VISIBLE
            activity_main_albums_list.visibility = View.GONE
            activity_main_swipe_layout.isRefreshing = false
        }

        AlbumsViewModel.State.Loading -> {
            activity_main_empty_label.visibility = View.GONE
            activity_main_albums_list.visibility = View.GONE
            activity_main_swipe_layout.isRefreshing = true
        }

        AlbumsViewModel.State.Empty -> {
            activity_main_empty_label.text = getString(R.string.activity_main_no_data)
            activity_main_empty_label.visibility = View.VISIBLE
            activity_main_albums_list.visibility = View.GONE
            activity_main_swipe_layout.isRefreshing = false
        }

        is AlbumsViewModel.State.Loaded -> {
            activity_main_empty_label.visibility = View.GONE
            activity_main_albums_list.visibility = View.VISIBLE
            adapter.replaceData(state.albums)
            activity_main_swipe_layout.isRefreshing = false
        }
    }
}
