package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.util.OnDataCallback

interface TopArtistsSource {
    fun getTopArtists(limit: Int, callback: OnDataCallback<List<Artist>>)
}