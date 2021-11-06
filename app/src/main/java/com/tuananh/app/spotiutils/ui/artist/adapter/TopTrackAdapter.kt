package com.tuananh.app.spotiutils.ui.artist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.databinding.ItemTopTracks2Binding
import com.tuananh.app.spotiutils.databinding.ItemTopTracksBinding
import com.tuananh.app.spotiutils.ui.top.page.tracks.adapter.TracksPageAdapter
import com.tuananh.app.spotiutils.util.getColorResCompat
import com.tuananh.app.spotiutils.util.hide
import com.tuananh.app.spotiutils.util.loadImage

class TopTrackAdapter(
    items: MutableList<Track>,
    val onItemClickListener: (Track) -> Unit
): BaseAdapter<Track, TopTrackAdapter.TopTrackViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopTrackViewHolder {
        return TopTrackViewHolder(
            ItemTopTracks2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    class TopTrackViewHolder(
        val viewBinding: ItemTopTracks2Binding,
        onItemClickListener: (Track) -> Unit
    ): BaseViewHolder<Track>(viewBinding, onItemClickListener) {

        override fun bind(item: Track) {
            super.bind(item)

            with(viewBinding) {
                textTopTrackRank.text = "$adapterPosition"
                imageTopTrack.loadImage(item.album.images[Image.ImageType.SIZE64_64.ordinal].url)
                textTopTrackName.text = item.name
                textTopTrackArtist.text = item.artists.map { artist -> artist.name }
                    .joinToString(separator = " - ")
            }
        }
    }

}