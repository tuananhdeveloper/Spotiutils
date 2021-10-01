package com.tuananh.app.spotiutils.data.local.source

import com.tuananh.app.spotiutils.data.remote.model.TokenData

interface TokenLocalSource {
    fun saveToken(tokenData: TokenData)
    fun readToken(): TokenData
}