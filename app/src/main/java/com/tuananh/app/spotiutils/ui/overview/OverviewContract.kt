package com.tuananh.app.spotiutils.ui.overview

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.Track

interface OverviewContract {

    interface View: BaseView {
        fun showLastPlayedTrack(track: RecentlyPlayedTrack)
        fun showLastPlayedLoading()
        fun hideLastPlayedLoading()

        fun showRecentlyPlayedTrack(tracks: MutableList<RecentlyPlayedTrack>)
        fun showRecentlyPlayedLoading()
        fun hideRecentlyPlayedLoading()

        fun showPlaylists(playlistsList: MutableList<PlaylistsItem>)
        fun showPlaylistsLoading()
        fun hidePlaylistsLoading()
    }

    interface Presenter {
        fun getLastPlayedTrack()
        fun getRecentlyPlayedTracks()
        fun getPlaylistsList()
    }
}