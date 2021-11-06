package com.tuananh.app.spotiutils.data.local.repository

import com.tuananh.app.spotiutils.data.local.source.TokenLocalSource
import com.tuananh.app.spotiutils.data.remote.model.TokenData

class TokenLocalRepository private constructor(
    private val tokenLocalSource: TokenLocalSource
): TokenLocalSource {
    override fun saveToken(tokenData: TokenData) {
        Thread {
            tokenLocalSource.saveToken(tokenData)
        }.start()
    }

    override fun readToken(): TokenData {
        return tokenLocalSource.readToken()
    }

    companion object {
        private var instance: TokenLocalRepository? = null

        fun getInstance(tokenLocalSource: TokenLocalSource) =
            instance ?: TokenLocalRepository(tokenLocalSource).also { instance = it }
    }
}