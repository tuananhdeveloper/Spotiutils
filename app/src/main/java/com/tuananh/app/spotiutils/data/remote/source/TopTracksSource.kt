package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.util.OnDataCallback

interface TopTracksSource {
    fun getTopTracks(limit: Int, callback: OnDataCallback<List<Track>>)
}