package com.tuananh.app.spotiutils

import com.tuananh.app.spotiutils.data.remote.model.TokenData

object PreparedData {
    private var token: TokenData? = null

    fun updateToken(tokenData: TokenData) {
        this.token = tokenData
    }

    fun getToken(): TokenData? = token
}