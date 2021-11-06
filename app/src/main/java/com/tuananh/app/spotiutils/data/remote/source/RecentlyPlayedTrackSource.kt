package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.util.OnDataCallback

interface RecentlyPlayedTrackSource {
    fun getLastPlayedTrack(callback: OnDataCallback<RecentlyPlayedTrack>)
    fun getRecentlyPlayedTracks(limit: Int, callback: OnDataCallback<List<RecentlyPlayedTrack>>)
}