package com.tuananh.app.spotiutils.data.remote.source.impl

import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.TrackSource
import com.tuananh.app.spotiutils.util.BaseConst
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.OnDataCallback
import com.tuananh.app.spotiutils.util.parseObjects
import org.json.JSONObject

class TrackSourceImpl private constructor(): TrackSource {

    override fun getTrack(id: String, callback: OnDataCallback<Track>) {
        MyAsyncTask<Unit, Track>(callback) {
            getTrack(id)
        }.execute()
    }

    override fun getAudioFeatures(trackId: String, callback: OnDataCallback<AudioFeatures>) {
        MyAsyncTask<Unit, AudioFeatures>(callback) {
            getAudioFeatures(trackId)
        }.execute()
    }

    private fun getAudioFeatures(trackId: String): AudioFeatures? {
        val response = RequestApi.doGet(ApiQuery.queryAudioFeatures(trackId))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                return AudioFeatures(JSONObject(response.responseBody))
            }
        }

        return null
    }

    private fun getTrack(id: String): Track? {
        PreparedData.getToken()?.let {
            val response = RequestApi.doGet(ApiQuery.queryTrack(id))
            if (response != null) {
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    return Track(JSONObject(response.responseBody))
                }
            }
        }
        return null
    }

    companion object {
        private var instance: TrackSourceImpl? = null
        fun getInstance() = instance ?: TrackSourceImpl().also { instance = it }
    }
}