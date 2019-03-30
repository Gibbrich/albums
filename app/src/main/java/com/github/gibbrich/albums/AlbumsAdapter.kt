package com.github.gibbrich.albums

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_album.view.*

class AlbumsAdapter(
    albums: List<Album>
) : ConstantValueAdapter<Album, AlbumsAdapter.Holder>(albums) {
    override fun createHolder(view: View): Holder = Holder(view)

    override val lineResourceId = R.layout.layout_item_album

    override fun bind(holder: Holder, item: Album, position: Int) {
        holder.itemView.layout_item_album_title.text = item.title
        holder.itemView.layout_item_album_id.text = item.id.toString()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view)
}