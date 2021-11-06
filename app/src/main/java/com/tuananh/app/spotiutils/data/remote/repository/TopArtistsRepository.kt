package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.PlaylistsSource
import com.tuananh.app.spotiutils.data.remote.source.TopArtistsSource
import com.tuananh.app.spotiutils.data.remote.source.TopTracksSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class TopArtistsRepository private constructor(
    private val topArtistsSource: TopArtistsSource
): TopArtistsSource {

    override fun getTopArtists(limit: Int, callback: OnDataCallback<List<Artist>>) {
        topArtistsSource.getTopArtists(limit, callback)
    }

    companion object {
        private var instance: TopArtistsRepository? = null

        fun getInstance(topArtistsSource: TopArtistsSource) =
            instance
                ?: TopArtistsRepository(topArtistsSource).also { instance = it }
    }
}