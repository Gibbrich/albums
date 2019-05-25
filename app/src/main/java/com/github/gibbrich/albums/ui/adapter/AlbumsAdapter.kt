package com.github.gibbrich.albums.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.github.gibbrich.albums.R
import com.github.gibbrich.core.model.Album
import kotlinx.android.synthetic.main.layout_item_album.view.*

class AlbumsAdapter(
    albums: MutableList<Album> = mutableListOf(),
    private val onAlbumClicked: (Long) -> Unit
) : ConstantValueAdapter<Album, AlbumsAdapter.Holder>(albums) {
    override fun createHolder(view: View): Holder =
        Holder(view, view.layout_item_album_title, view.layout_item_album_id)

    override val lineResourceId = R.layout.layout_item_album

    override fun bind(holder: Holder, item: Album, position: Int) {
        holder.titleView.text = item.title
        holder.idView.text = (position + 1).toString()
        holder.itemView.setOnClickListener { onAlbumClicked.invoke(item.id) }
    }

    class Holder(
        view: View,
        val titleView: TextView,
        val idView: TextView
    ) : RecyclerView.ViewHolder(view)
}