package com.tuananh.app.spotiutils.data.remote.api

import android.util.Log
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.local.repository.TokenLocalRepository
import com.tuananh.app.spotiutils.data.local.source.impl.TokenLocalSourceImpl
import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.data.remote.repository.TokenRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.TokenDataSourceImpl
import com.tuananh.app.spotiutils.util.*
import org.json.JSONObject
import java.lang.Exception

object RequestApi {

    private fun saveAndUpdateToken(tokenData: TokenData) {
        PreparedData.updateToken(tokenData)
        TokenLocalRepository.getInstance(TokenLocalSourceImpl.getInstance())
            .saveToken(tokenData)
    }

    @Synchronized
    fun doGet(url: String): Response? {
        PreparedData.getToken()?.let { tokenData ->
            Log.d("token_expire", tokenData.expireAt.time.toString())
            Log.d("current_time", System.currentTimeMillis().toString())

            var newTokenData: TokenData? = null

            if(tokenData.expire()) {
                val response0 = NetworkUtil.requestToken(ApiQuery.queryRefreshedToken(TokenDataSourceImpl.GRANT_TYPE_REFRESHED_TOKEN,
                tokenData.refreshToken))
                if(response0.statusCode == ApiConstants.Status.CODE_200) {
                    var jsonObject = JSONObject(response0.responseBody)
                    jsonObject.put(TokenDataConst.REFRESH_TOKEN, tokenData.refreshToken)
                    newTokenData = TokenData(jsonObject)
                    saveAndUpdateToken(newTokenData)
                }
            }

            newTokenData?.let { newTokenData
                val response = NetworkUtil.performGetRequest(url, newTokenData.accessToken)
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    return response
                }
            } ?: run {
                val response = NetworkUtil.performGetRequest(url, tokenData.accessToken)
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    return response
                }
            }

            return null
        }
        return null
    }

    @Synchronized
    fun doPost(url: String, requestBody: String): Response? {
        PreparedData.getToken()?.let { tokenData ->
            Log.d("token_expire", tokenData.expireAt.time.toString())
            Log.d("current_time", System.currentTimeMillis().toString())

            var newTokenData: TokenData? = null

            if(tokenData.expire()) {
                val response0 = NetworkUtil.requestToken(ApiQuery.queryRefreshedToken(TokenDataSourceImpl.GRANT_TYPE_REFRESHED_TOKEN,
                    tokenData.refreshToken))
                if(response0.statusCode == ApiConstants.Status.CODE_200) {
                    var jsonObject = JSONObject(response0.responseBody)
                    jsonObject.put(TokenDataConst.REFRESH_TOKEN, tokenData.refreshToken)
                    newTokenData = TokenData(jsonObject)
                    saveAndUpdateToken(newTokenData)
                }
            }

            newTokenData?.let { newTokenData
                val response = NetworkUtil.performPostRequest(url, newTokenData.accessToken, requestBody)
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    return response
                }
            } ?: run {
                val response = NetworkUtil.performPostRequest(url, tokenData.accessToken, requestBody)
                if(response.statusCode == ApiConstants.Status.CODE_200) {
                    return response
                }
            }

            return null
        }
        return null
    }

}