package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.util.OnDataCallback

interface PlaylistsSource {
    fun getPlaylistsList(callback: OnDataCallback<List<PlaylistsItem>>)
}