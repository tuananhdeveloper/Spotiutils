package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.source.PlaylistsSource
import com.tuananh.app.spotiutils.data.remote.source.RecentlyPlayedTrackSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class PlaylistsRepository private constructor(
    private val playlistsSource: PlaylistsSource
): PlaylistsSource {

    override fun getPlaylistsList(callback: OnDataCallback<List<PlaylistsItem>>) {
        playlistsSource.getPlaylistsList(callback)
    }

    companion object {
        private var instance: PlaylistsRepository? = null

        fun getInstance(playlistsSource: PlaylistsSource) =
            instance
                ?: PlaylistsRepository(playlistsSource).also { instance = it }
    }
}