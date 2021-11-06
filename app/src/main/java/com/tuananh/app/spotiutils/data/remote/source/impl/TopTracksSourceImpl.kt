package com.tuananh.app.spotiutils.data.remote.source.impl

import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.TopTracksSource
import com.tuananh.app.spotiutils.util.BaseConst
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.OnDataCallback
import com.tuananh.app.spotiutils.util.parseObjects
import org.json.JSONObject

class TopTracksSourceImpl private constructor(): TopTracksSource {

    override fun getTopTracks(limit: Int,callback: OnDataCallback<List<Track>>) {
        MyAsyncTask<Unit, List<Track>>(callback) {
            getTopTracks(limit)
        }.execute()
    }

    private fun getTopTracks(limit: Int): List<Track>? {
        val response = RequestApi.doGet(ApiQuery.queryTopTracks(limit, TIME_RAGE))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                return JSONObject(response.responseBody)
                    .getString(BaseConst.ITEMS)
                    .parseObjects()
            }
        }
        return null
    }

    companion object {
        const val TIME_RAGE = "long_term"
        private var instance: TopTracksSourceImpl? = null
        fun getInstance() = instance ?: TopTracksSourceImpl().also { instance = it }
    }
}