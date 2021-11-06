package com.tuananh.app.spotiutils.ui.track.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.databinding.CardviewTrackBinding
import com.tuananh.app.spotiutils.util.loadImage

class MoreTrackFromArtistAdapter(
    items: MutableList<Track>,
    private val onItemClickListener: (Track) -> Unit
): BaseAdapter<Track, MoreTrackFromArtistAdapter.MoreTrackFromArtistVH>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreTrackFromArtistVH {
        return MoreTrackFromArtistVH(
            CardviewTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    class MoreTrackFromArtistVH(
        private val viewBinding: CardviewTrackBinding,
        onItemClickListener: (Track) -> Unit
    ): BaseViewHolder<Track>(viewBinding, onItemClickListener) {

        override fun bind(item: Track) {
            super.bind(item)
            viewBinding.apply {
                imageTrack.loadImage(item.album.images[Image.ImageType.SIZE300_300.ordinal].url)
                textTrackName.text = item.name
            }
        }
    }
}