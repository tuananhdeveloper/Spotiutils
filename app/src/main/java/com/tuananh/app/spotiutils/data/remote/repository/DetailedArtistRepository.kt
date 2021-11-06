package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.ArtistSource
import com.tuananh.app.spotiutils.data.remote.source.DetailedArtistSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class DetailedArtistRepository private constructor(
    private val artistSource: DetailedArtistSource
): DetailedArtistSource {

    override fun getArtist(ids: List<String>, callback: OnDataCallback<List<Artist>>) {
        artistSource.getArtist(ids, callback)
    }

    override fun getRelatedArtists(id: String, callback: OnDataCallback<List<Artist>>) {
        artistSource.getRelatedArtists(id, callback)
    }

    override fun getTopTracks(id: String, callback: OnDataCallback<List<Track>>) {
        artistSource.getTopTracks(id, callback)
    }

    companion object {
        private var instance: DetailedArtistRepository? = null
        fun getInstance(artistSource: DetailedArtistSource) =
            instance ?: DetailedArtistRepository(artistSource).also { instance = it }
    }
}