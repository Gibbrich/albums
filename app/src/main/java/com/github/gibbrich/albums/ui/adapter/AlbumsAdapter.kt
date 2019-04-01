package com.github.gibbrich.albums.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.gibbrich.albums.R
import com.github.gibbrich.albums.domain.model.Album
import kotlinx.android.synthetic.main.layout_item_album.view.*

class AlbumsAdapter(
    albums: MutableList<Album> = mutableListOf()
) : ConstantValueAdapter<Album, AlbumsAdapter.Holder>(albums) {
    override fun createHolder(view: View): Holder =
        Holder(view)

    override val lineResourceId = R.layout.layout_item_album

    override fun bind(holder: Holder, item: Album, position: Int) {
        holder.itemView.layout_item_album_title.text = item.title
        holder.itemView.layout_item_album_id.text = item.id.toString()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view)
}