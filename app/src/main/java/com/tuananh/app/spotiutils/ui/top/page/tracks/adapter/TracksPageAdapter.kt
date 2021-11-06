package com.tuananh.app.spotiutils.ui.top.page.tracks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.databinding.ItemTopTracksBinding
import com.tuananh.app.spotiutils.util.loadImage

class TracksPageAdapter(
    items: MutableList<Track>,
    open val onItemClickListener: (Track) -> Unit
): BaseAdapter<Track, TracksPageAdapter.TrackPageViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackPageViewHolder {
        return TrackPageViewHolder(
            ItemTopTracksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    open class TrackPageViewHolder(
        open val viewBinding: ItemTopTracksBinding,
         onItemClickListener: (Track) -> Unit
    ): BaseViewHolder<Track>(viewBinding, onItemClickListener) {

        override fun bind(item: Track) {
            super.bind(item)

            with(viewBinding) {
                textTopTrackRank.text = "#$adapterPosition"
                imageTopTrack.loadImage(item.album.images[Image.ImageType.SIZE64_64.ordinal].url)
                textTopTrackName.text = item.name
                textTopTrackArtist.text = item.artists.map { artist -> artist.name }
                    .joinToString(separator = " - ")

            }
        }
    }
}