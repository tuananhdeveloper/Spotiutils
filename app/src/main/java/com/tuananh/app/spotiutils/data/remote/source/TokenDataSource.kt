package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.util.OnDataCallback

interface TokenDataSource {
    fun getToken(callback: OnDataCallback<TokenData>)
    fun getRefreshedToken(refreshToken: String, callback: OnDataCallback<TokenData>)
}