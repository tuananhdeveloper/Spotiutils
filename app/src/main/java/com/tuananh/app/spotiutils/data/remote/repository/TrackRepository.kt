package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.ArtistSource
import com.tuananh.app.spotiutils.data.remote.source.TopTracksSource
import com.tuananh.app.spotiutils.data.remote.source.TrackSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class TrackRepository private constructor(
    private val trackSource: TrackSource,
    private val artistSource: ArtistSource
): TrackSource, ArtistSource {

    override fun getTopTracks(id: String, callback: OnDataCallback<List<Track>>) {
        artistSource.getTopTracks(id, callback)
    }

    override fun getArtist(ids: List<String>, callback: OnDataCallback<List<Artist>>) {
        artistSource.getArtist(ids, callback)
    }

    override fun getTrack(id: String, callback: OnDataCallback<Track>) {
        trackSource.getTrack(id, callback)
    }

    override fun getAudioFeatures(trackId: String, callback: OnDataCallback<AudioFeatures>) {
        trackSource.getAudioFeatures(trackId, callback)
    }

    companion object {

        private var instance: TrackRepository? = null
        fun getInstance(trackSource: TrackSource, artistSource: ArtistSource) =
            instance
                ?: TrackRepository(trackSource, artistSource).also { instance = it }
    }
}