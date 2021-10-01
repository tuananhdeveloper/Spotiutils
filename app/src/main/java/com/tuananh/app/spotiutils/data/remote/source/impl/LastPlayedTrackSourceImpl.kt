package com.tuananh.app.spotiutils.data.remote.source.impl

import android.widget.Toast
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.LastPlayedTrackSource
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.NetworkUtil
import com.tuananh.app.spotiutils.util.OnDataCallback

class LastPlayedTrackSourceImpl: LastPlayedTrackSource {

    override fun getLastPlayedTrack(callback: OnDataCallback<Track>) {
        MyAsyncTask<Unit, Track>(callback) {
            getLastPlayedTrack()
        }
    }

    private fun getLastPlayedTrack(): Track? {
        PreparedData.getToken()?.let {
            val response = NetworkUtil.performGetRequest(ApiQuery.queryLastPlayedTrack(), it.accessToken)
            if(response.statusCode == ApiConstants.Status.CODE_200) {


            }
            else {
                if(response.statusCode == ApiConstants.Status.CODE_401) {

                }
                else {

                }
            }
        } ?: run {
            return null
        }
        return null
    }
}