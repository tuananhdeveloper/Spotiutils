package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.util.OnDataCallback

interface DetailedArtistSource: ArtistSource {
    fun getRelatedArtists(id: String, callback: OnDataCallback<List<Artist>>)
}