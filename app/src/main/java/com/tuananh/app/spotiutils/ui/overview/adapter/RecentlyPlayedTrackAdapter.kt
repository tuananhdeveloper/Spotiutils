package com.tuananh.app.spotiutils.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.databinding.ItemRecentlyPlayedBinding
import com.tuananh.app.spotiutils.util.getDifference
import com.tuananh.app.spotiutils.util.loadImage

class RecentlyPlayedTrackAdapter(
    items: MutableList<RecentlyPlayedTrack>,
    private val onItemClickListener: (RecentlyPlayedTrack) -> Unit
): BaseAdapter<RecentlyPlayedTrack, RecentlyPlayedTrackAdapter.RecentlyPlayedTrackViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyPlayedTrackViewHolder {
        return RecentlyPlayedTrackViewHolder(
            ItemRecentlyPlayedBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    class RecentlyPlayedTrackViewHolder(
        private val viewbinding: ItemRecentlyPlayedBinding,
        onItemClickListener: (RecentlyPlayedTrack) -> Unit
    ): BaseViewHolder<RecentlyPlayedTrack>(viewbinding, onItemClickListener) {

        override fun bind(item: RecentlyPlayedTrack) {
            super.bind(item)

            with(viewbinding) {
                with(item) {
                    with(track) {
                        imageRecentlyPlayed.loadImage(album.images[Image.ImageType.SIZE64_64.ordinal].url)
                        textRecentlyPlayedName.text = this.name
                        textRecentlyPlayedArtist.text = this.artists
                            .map { artist -> artist.name }
                            .joinToString(separator = " - ")
                    }
                    textRecentlyPlayedHistory.text = playedAt.getDifference()
                }
            }
        }
    }
}