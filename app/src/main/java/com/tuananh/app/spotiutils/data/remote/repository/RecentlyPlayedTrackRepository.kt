package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.RecentlyPlayedTrackSource
import com.tuananh.app.spotiutils.util.OnDataCallback
import java.io.File

class RecentlyPlayedTrackRepository private constructor(
    private val recentlyPlayedTrackSource: RecentlyPlayedTrackSource
): RecentlyPlayedTrackSource {

    override fun getLastPlayedTrack(callback: OnDataCallback<RecentlyPlayedTrack>) {
        recentlyPlayedTrackSource.getLastPlayedTrack(callback)
    }

    override fun getRecentlyPlayedTracks(limit: Int, callback: OnDataCallback<List<RecentlyPlayedTrack>>) {
        recentlyPlayedTrackSource.getRecentlyPlayedTracks(limit, callback)
    }

    companion object {
        private var instance: RecentlyPlayedTrackRepository? = null

        fun getInstance(recentlyPlayedTrackSource: RecentlyPlayedTrackSource) =
            instance
                ?: RecentlyPlayedTrackRepository(recentlyPlayedTrackSource).also { instance = it }
    }
}