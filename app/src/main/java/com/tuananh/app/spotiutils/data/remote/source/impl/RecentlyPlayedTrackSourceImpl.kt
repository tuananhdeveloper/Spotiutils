package com.tuananh.app.spotiutils.data.remote.source.impl

import android.util.Log
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.RecentlyPlayedTrackSource
import com.tuananh.app.spotiutils.util.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.prefs.BackingStoreException

class RecentlyPlayedTrackSourceImpl private constructor(): RecentlyPlayedTrackSource {

    override fun getLastPlayedTrack(callback: OnDataCallback<RecentlyPlayedTrack>) {
        MyAsyncTask<Unit, RecentlyPlayedTrack>(callback) {
            getLastPlayedTrack()
        }.execute()
    }

    override fun getRecentlyPlayedTracks(limit: Int, callback: OnDataCallback<List<RecentlyPlayedTrack>>) {
        MyAsyncTask<Unit, List<RecentlyPlayedTrack>>(callback) {
            getRecentlyPlayedTracks(limit)
        }.execute()
    }

    private fun getRecentlyPlayedTracks(limit: Int): MutableList<RecentlyPlayedTrack>? {
        val response = RequestApi.doGet(ApiQuery.queryRecentlyPlayedTrack(limit))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                Log.d("my_log_Track", response.toString())

                return JSONObject(response.responseBody)
                    .getString(BaseConst.ITEMS)
                    .parseObjects()
            } else {
                return null
            }
        }
        return null
    }

    private fun getLastPlayedTrack(): RecentlyPlayedTrack? {
        PreparedData.getToken()?.let {
            val response = RequestApi.doGet(ApiQuery.queryRecentlyPlayedTrack())
            if (response != null) {
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    Log.d("my_log_Track", response.toString())

                    val jsonObject = JSONObject(response.responseBody)
                        .getJSONArray(BaseConst.ITEMS)
                        .getJSONObject(0)
                    return RecentlyPlayedTrack(jsonObject)
                } else {
                    return null
                }
            }
            else return null
        } ?: run {
            return null
        }
    }

    companion object {
        private var instance: RecentlyPlayedTrackSourceImpl? = null
        fun getInstance() = instance ?: RecentlyPlayedTrackSourceImpl().also { instance = it }
    }
}