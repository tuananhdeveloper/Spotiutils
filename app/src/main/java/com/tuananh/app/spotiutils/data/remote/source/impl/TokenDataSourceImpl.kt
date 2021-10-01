package com.tuananh.app.spotiutils.data.remote.source.impl

import com.tuananh.app.spotiutils.data.local.source.impl.AuthCodeSourceImpl
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.data.remote.source.TokenDataSource
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.NetworkUtil
import com.tuananh.app.spotiutils.util.OnDataCallback
import org.json.JSONObject

class TokenDataSourceImpl private constructor(): TokenDataSource {

    override fun getToken(callback: OnDataCallback<TokenData>) {
        MyAsyncTask<Unit, TokenData>(callback) {
            getToken()
        }.execute(Unit)
    }

    //run in background
    private fun getToken(): TokenData? {
        val authCode = AuthCodeSourceImpl.getInstance().getAuthCode()
        authCode?.let {
            val response = NetworkUtil.requestToken(ApiQuery.queryToken(GRANT_TYPE, authCode, REDIRECT_URI))
            if(response.statusCode == ApiConstants.Status.CODE_200) {
                return TokenData(JSONObject(response.responseBody))
            }
        }
        return null
    }

    companion object {
        private const val REDIRECT_URI = WebViewActivity.REDIRECT_URI
        private const val GRANT_TYPE = "authorization_code"

        private var instance: TokenDataSourceImpl? = null

        fun getInstance() = instance ?: TokenDataSourceImpl().also { instance = it }
    }
}