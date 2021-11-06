package com.tuananh.app.spotiutils.data.remote.source.impl

import android.util.Log
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.User
import com.tuananh.app.spotiutils.data.remote.source.CurrentUserProfileSource
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.OnDataCallback
import org.json.JSONObject

class CurrentUserProfileSourceImpl private constructor(): CurrentUserProfileSource{

    override fun getCurrentUserProfile(callback: OnDataCallback<User>) {
        MyAsyncTask<Unit, User>(callback) {
            getCurrentUserProfile()
        }.execute()
    }

    private fun getCurrentUserProfile(): User? {
        val response = RequestApi.doGet(ApiQuery.queryCurrentUserProfile())
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                Log.d("my_log_user", response.toString())
                val jsonObject = JSONObject(response.responseBody)
                return User(jsonObject)
            }
        }
        return null
    }

    companion object {
        private var instance: CurrentUserProfileSourceImpl? = null
        fun getInstance() = instance ?: CurrentUserProfileSourceImpl().also { instance = it }
    }
}