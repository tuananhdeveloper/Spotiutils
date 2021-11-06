package com.tuananh.app.spotiutils.ui.artist

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.DetailedArtistRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.ArtistSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentArtistBinding
import com.tuananh.app.spotiutils.ui.artist.adapter.RelatedArtistAdapter
import com.tuananh.app.spotiutils.ui.artist.adapter.TopTrackAdapter
import com.tuananh.app.spotiutils.ui.top.page.tracks.adapter.TracksPageAdapter
import com.tuananh.app.spotiutils.ui.track.TrackFragment
import com.tuananh.app.spotiutils.ui.track.adapter.ArtistAdapter
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity
import com.tuananh.app.spotiutils.util.*

class ArtistFragment: BaseFragment<FragmentArtistBinding>(), ArtistContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistBinding
        get() = FragmentArtistBinding::inflate

    private var artist: Artist? = null

    private val topTrackAdapter by lazy {
        TopTrackAdapter(
            mutableListOf(),
            this::onItemClickListener
        )
    }

    private val artistAdapter by lazy {
        RelatedArtistAdapter(
            mutableListOf(),
            this::onArtistClickListener
        )
    }

    override fun initData() {
        artist = arguments?.getParcelable(EXTRA_ARTIST)
        artist?.let {
            showArtist(it)
            ArtistPresenter(this,
                DetailedArtistRepository.getInstance(
                    ArtistSourceImpl.getInstance()
                )).run {
                getTopTracks(it.id)
                getRelatedArtists(it.id)
            }
        }
    }

    override fun initView() {
        viewBinding?.run {

            appbarFragmentArtist.toolbar.apply {
                inflateMenu(R.menu.menu_fragment_track_artist)
                setNavigationOnClickListener {
                    if(isAdded) {
                        parentFragmentManager.popBackStack()
                    }
                }

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_favorite -> {
                            addToFavorites()
                            true
                        }
                        else -> true
                    }
                }

            }

            appbarFragmentArtist.collapsingToolbar.apply {
                title = ""
                setExpandedTitleColor(Color.WHITE)
                setCollapsedTitleTextColor(Color.WHITE)
                setContentScrimColor(ContextCompat.getColor(context, R.color.primaryColor))
            }

            recyclerviewTopTracks.apply {
                layoutManager = GridLayoutManager(context, SPAN_COUNT,
                    GridLayoutManager.HORIZONTAL, false)
                adapter = topTrackAdapter
            }

            recyclerviewRelatedArtists.apply {
                layoutManager = GridLayoutManager(context, SPAN_COUNT,
                    GridLayoutManager.HORIZONTAL, false)
                adapter = artistAdapter
            }
        }
    }

    override fun showTopTracks(tracks: List<Track>) {
        topTrackAdapter.updateData(tracks as MutableList<Track>)
    }

    override fun showRelatedArtists(artists: List<Artist>) {
        artistAdapter.updateData(artists as MutableList<Artist>)
    }

    override fun showLoading() {
        viewBinding?.loading?.show()
    }

    override fun hideLoading() {
        viewBinding?.loading?.hide()
    }

    override fun showAllView() {
        viewBinding?.run {
            cardviewPopularity.root.show()
            cardviewFollowers.root.show()
            textTopTracks.hide()
            recyclerviewTopTracks.show()
            textRelatedArtists.show()
            recyclerviewRelatedArtists.show()
        }
    }

    override fun hideAllView() {
        viewBinding?.run {
            cardviewPopularity.root.hide()
            cardviewFollowers.root.hide()
            textTopTracks.hide()
            recyclerviewTopTracks.hide()
            textRelatedArtists.hide()
            recyclerviewRelatedArtists.hide()
        }
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun showArtist(artist: Artist) {
        viewBinding?.run {
            appbarFragmentArtist.apply {
                artist.images?.let {
                    image.loadImage(it[Image.ImageType.SIZE300_300.ordinal].url)
                }
                toolbar.title = artist.name
            }

            cardviewFollowers.apply {
                this.textFollowers.text = artist.followers?.total.toString()
            }

            cardviewPopularity.apply {
                textPopularity.text = (artist.popularity?.toDouble()?.div(10)).toString()
            }
        }
    }

    private fun onItemClickListener(track: Track) {
        openNewTrackFragment(track)
    }

    private fun onArtistClickListener(artist: Artist) {
        openNewArtistFragment(artist)
    }

    private fun addToFavorites() {
        Toast.makeText(context, "add to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun openNewTrackFragment(track: Track) {
        val trackFragment = TrackFragment()
        trackFragment.arguments = bundleOf(TrackFragment.EXTRA_TRACK to track)
        fragmentManager?.addFragment(R.id.fragment_container,
            trackFragment,
            null,
            true
        )
    }

    private fun openNewArtistFragment(artist: Artist) {
        val artistFragment = ArtistFragment()
        artistFragment.arguments = bundleOf(ArtistFragment.EXTRA_ARTIST to artist)
        fragmentManager?.addFragment(R.id.fragment_container,
            artistFragment,
            null,
            true
        )
    }

    companion object {
        const val EXTRA_ARTIST = "extra_artist"
        const val SPAN_COUNT = 2
    }
}