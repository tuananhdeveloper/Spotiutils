package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.data.remote.source.TokenDataSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class TokenRepository private constructor(
    private val tokenDataSource: TokenDataSource
): TokenDataSource {

    override fun getToken(callback: OnDataCallback<TokenData>) {
        tokenDataSource.getToken(callback)
    }

    override fun getRefreshedToken(refreshToken: String, callback: OnDataCallback<TokenData>) {
        tokenDataSource.getRefreshedToken(refreshToken, callback)
    }

    companion object {
        private var instance: TokenRepository? = null

        fun getInstance(tokenDataSource: TokenDataSource) =
            instance ?: TokenRepository(tokenDataSource).also { instance = it }
    }
}