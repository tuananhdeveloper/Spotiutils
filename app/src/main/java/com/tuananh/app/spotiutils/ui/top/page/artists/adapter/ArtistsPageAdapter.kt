package com.tuananh.app.spotiutils.ui.top.page.artists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.databinding.ItemTopArtistsBinding
import com.tuananh.app.spotiutils.util.loadImage

class ArtistsPageAdapter(
    items: MutableList<Artist>,
    val onItemClickListener: (Artist) -> Unit
): BaseAdapter<Artist, ArtistsPageAdapter.ArtistsPageViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsPageViewHolder {
        return ArtistsPageViewHolder(
            ItemTopArtistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    class ArtistsPageViewHolder(
        val viewBinding: ItemTopArtistsBinding,
        onItemClickListener: (Artist) -> Unit
    ): BaseViewHolder<Artist>(viewBinding, onItemClickListener) {

        override fun bind(item: Artist) {
            super.bind(item)
            with(viewBinding) {
                textTopArtistRank.text = "#$adapterPosition"
                textTopArtistName.text = item.name
                item.images?.let {
                    imageTopArtist.loadImage(it[Image.ImageType.SIZE64_64.ordinal].url)
                }
            }
        }
    }
}