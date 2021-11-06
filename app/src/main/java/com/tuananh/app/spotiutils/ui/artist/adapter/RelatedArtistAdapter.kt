package com.tuananh.app.spotiutils.ui.artist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuananh.app.spotiutils.base.BaseAdapter
import com.tuananh.app.spotiutils.base.BaseViewHolder
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.databinding.CardviewArtist2Binding
import com.tuananh.app.spotiutils.databinding.CardviewArtistBinding
import com.tuananh.app.spotiutils.util.loadImage


class RelatedArtistAdapter(
    items: MutableList<Artist>,
    private val onItemClickListener: (Artist) -> Unit
): BaseAdapter<Artist, RelatedArtistAdapter.ArtistViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            CardviewArtist2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    class ArtistViewHolder(
        private val viewBinding: CardviewArtist2Binding,
        onItemClickListener: (Artist) -> Unit
    ): BaseViewHolder<Artist>(viewBinding, onItemClickListener) {

        override fun bind(item: Artist) {
            super.bind(item)
            viewBinding.apply {
                item.images?.let {
                    imageArtist.loadImage(it[Image.ImageType.SIZE300_300.ordinal].url)
                }
                textArtistName.text = item.name
            }
        }
    }
}