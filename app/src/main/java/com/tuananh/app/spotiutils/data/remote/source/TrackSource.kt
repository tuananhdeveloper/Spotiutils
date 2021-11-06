package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.util.OnDataCallback

interface TrackSource {
    fun getTrack(id: String, callback: OnDataCallback<Track>)
    fun getAudioFeatures(trackId: String, callback: OnDataCallback<AudioFeatures>)
}