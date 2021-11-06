package com.tuananh.app.spotiutils.ui.overview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.repository.PlaylistsRepository
import com.tuananh.app.spotiutils.data.remote.repository.RecentlyPlayedTrackRepository
import com.tuananh.app.spotiutils.data.remote.repository.TokenRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.PlaylistsSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.RecentlyPlayedTrackSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.TokenDataSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentOverviewBinding
import com.tuananh.app.spotiutils.ui.overview.adapter.PlaylistsAdapter
import com.tuananh.app.spotiutils.ui.overview.adapter.RecentlyPlayedTrackAdapter
import com.tuananh.app.spotiutils.ui.track.TrackFragment
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity
import com.tuananh.app.spotiutils.util.addFragment
import com.tuananh.app.spotiutils.util.loadImage
import com.tuananh.app.spotiutils.util.replaceFragment
import kotlin.concurrent.fixedRateTimer

class OverviewFragment: BaseFragment<FragmentOverviewBinding>(), OverviewContract.View {

    private val recentlyPlayedTrackAdapter by lazy {
        RecentlyPlayedTrackAdapter(
            mutableListOf(),
            this::onRecentlyPlayedItemClick
        )
    }

    private val playlistsAdapter by lazy {
        PlaylistsAdapter(
            mutableListOf(),
            this::onPlaylistsItemClick
        )
    }

    private fun updateCardView(data: RecentlyPlayedTrack) {
        viewBinding?.run {
            with(cardviewLastPlayed) {
                imageLastPlayedTrack.loadImage(data.track.album
                    .images[Image.ImageType.SIZE64_64.ordinal].url)
                textLastPlayedTrackName.text = data.track.name
                textLastPlayedTrackArtist.text = data.track.artists
                    .map { artist -> artist.name }
                    .joinToString(separator = " - ")

                root.setOnClickListener {
                    onRecentlyPlayedItemClick(data)
                }
            }
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOverviewBinding
        get() = FragmentOverviewBinding::inflate

    override fun initView() {
        viewBinding?.run {
            with(recyclerviewRecentlyPlayed) {
                adapter = recentlyPlayedTrackAdapter
                layoutManager = LinearLayoutManager(context)
            }
            with(recyclerviewPlaylists) {
                adapter = playlistsAdapter
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            }
        }
    }

    override fun initData() {
        OverviewPresenter(this,
            RecentlyPlayedTrackRepository.getInstance(RecentlyPlayedTrackSourceImpl.getInstance()),
        PlaylistsRepository.getInstance(PlaylistsSourceImpl.getInstance()))
            .run {
                getRecentlyPlayedTracks()
                getLastPlayedTrack()
                getPlaylistsList()
            }
    }

    override fun showLastPlayedTrack(track: RecentlyPlayedTrack) {
        updateCardView(track)
        Log.d("my_log_track", track.toString())
    }

    override fun showLastPlayedLoading() {
        viewBinding?.cardviewLastPlayed?.loading?.show()
    }

    override fun hideLastPlayedLoading() {
        viewBinding?.cardviewLastPlayed?.loading?.hide()
    }

    override fun showRecentlyPlayedTrack(tracks: MutableList<RecentlyPlayedTrack>) {
        recentlyPlayedTrackAdapter.updateData(tracks)
        Log.d("my_log_track", tracks.size.toString())
    }

    override fun showRecentlyPlayedLoading() {
        viewBinding?.loadingRecentlyPlayed?.show()
    }

    override fun hideRecentlyPlayedLoading() {
        viewBinding?.loadingRecentlyPlayed?.hide()
    }

    override fun showPlaylists(playlistsList: MutableList<PlaylistsItem>) {
        playlistsAdapter.updateData(playlistsList)

    }

    override fun showPlaylistsLoading() {
        viewBinding?.loadingPlaylists?.show()
    }

    override fun hidePlaylistsLoading() {
        viewBinding?.loadingPlaylists?.hide()
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, getString(messageRes), Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun onRecentlyPlayedItemClick(item: RecentlyPlayedTrack) {
        val trackFragment = TrackFragment()
        trackFragment.arguments = bundleOf(TrackFragment.EXTRA_TRACK to item.track)
        fragmentManager?.addFragment(R.id.fragment_container,
            trackFragment,
            null,
            true
        )
    }

    private fun onPlaylistsItemClick(item: PlaylistsItem) {
        Toast.makeText(context, item.name, Toast.LENGTH_LONG).show()
    }
}