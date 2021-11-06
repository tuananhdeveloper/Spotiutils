package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.PlaylistsSource
import com.tuananh.app.spotiutils.data.remote.source.TopTracksSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class TopTracksRepository private constructor(
    private val topTracksSource: TopTracksSource
): TopTracksSource {

    override fun getTopTracks(limit: Int, callback: OnDataCallback<List<Track>>) {
        topTracksSource.getTopTracks(limit, callback)
    }

    companion object {
        private var instance: TopTracksRepository? = null

        fun getInstance(topTracksSource: TopTracksSource) =
            instance
                ?: TopTracksRepository(topTracksSource).also { instance = it }
    }
}