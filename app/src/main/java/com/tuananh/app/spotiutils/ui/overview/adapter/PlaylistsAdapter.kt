package com.tuananh.app.spotiutils.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.databinding.ItemPlaylistsBinding
import com.tuananh.app.spotiutils.databinding.ItemRecentlyPlayedBinding
import com.tuananh.app.spotiutils.util.getDifference
import com.tuananh.app.spotiutils.util.loadImage

class PlaylistsAdapter(
    items: MutableList<PlaylistsItem>,
    private val onItemClickListener: (PlaylistsItem) -> Unit
): BaseAdapter<PlaylistsItem, PlaylistsAdapter.PlaylistsViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemPlaylistsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    class PlaylistsViewHolder(
        private val viewbinding: ItemPlaylistsBinding,
        onItemClickListener: (PlaylistsItem) -> Unit
    ): BaseViewHolder<PlaylistsItem>(viewbinding, onItemClickListener) {

        override fun bind(item: PlaylistsItem) {
            super.bind(item)

            with(viewbinding) {
                imagePlaylists.loadImage(item.images[Image.ImageType.SIZE64_64.ordinal].url)
                textPlaylistName.text= item.name
                textPlaylistOwner.text = item.owner.displayName
            }
        }
    }
}