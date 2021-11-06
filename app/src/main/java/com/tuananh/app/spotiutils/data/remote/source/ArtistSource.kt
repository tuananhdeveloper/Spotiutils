package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.util.OnDataCallback

interface ArtistSource {
    fun getTopTracks(id: String, callback: OnDataCallback<List<Track>>)
    fun getArtist(ids: List<String>, callback: OnDataCallback<List<Artist>>)
}
