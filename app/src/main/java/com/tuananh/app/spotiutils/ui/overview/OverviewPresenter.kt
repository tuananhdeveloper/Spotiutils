package com.tuananh.app.spotiutils.ui.overview

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.repository.PlaylistsRepository
import com.tuananh.app.spotiutils.data.remote.repository.RecentlyPlayedTrackRepository
import com.tuananh.app.spotiutils.data.remote.repository.TokenRepository
import com.tuananh.app.spotiutils.util.OnDataCallback

class OverviewPresenter(
    private val view: OverviewContract.View,
    private val playedTrackRepository: RecentlyPlayedTrackRepository,
    private val playlistsRepository: PlaylistsRepository,
): OverviewContract.Presenter {

    override fun getLastPlayedTrack() {
        view.showLastPlayedLoading()
        requestLastPlayedTrack()
    }

    override fun getRecentlyPlayedTracks() {
        view.showRecentlyPlayedLoading()
        requestRecentlyPlayedTrack()
    }

    override fun getPlaylistsList() {
        view.showPlaylistsLoading()
        requestPlaylistsList()

    }

    private fun requestPlaylistsList() {
        playlistsRepository.getPlaylistsList(object : OnDataCallback<List<PlaylistsItem>> {
            override fun onResponse(data: List<PlaylistsItem>) {
                view.hidePlaylistsLoading()
                view.showPlaylists(data as MutableList<PlaylistsItem>)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hidePlaylistsLoading()
                view.showMessage(R.string.error_playlists)
            }
        })
    }

    private fun requestRecentlyPlayedTrack() {
        playedTrackRepository.getRecentlyPlayedTracks(RECENTLY_ITEMS_LIMIT, object : OnDataCallback<List<RecentlyPlayedTrack>> {
            override fun onResponse(data: List<RecentlyPlayedTrack>) {
                view.hideRecentlyPlayedLoading()
                view.showRecentlyPlayedTrack(data as MutableList<RecentlyPlayedTrack>)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hideRecentlyPlayedLoading()
                view.showMessage(R.string.error_recently_played_track)
            }
        })
    }

    private fun requestLastPlayedTrack() {
        view.showLastPlayedLoading()
        playedTrackRepository.getLastPlayedTrack(object: OnDataCallback<RecentlyPlayedTrack> {

            override fun onResponse(data: RecentlyPlayedTrack) {
                view.hideLastPlayedLoading()
                view.showLastPlayedTrack(data)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hideLastPlayedLoading()
                view.showMessage(R.string.error_data_last_played_track)
            }
        })
    }

    companion object {
        const val RECENTLY_ITEMS_LIMIT = 20
    }
}