package com.tuananh.app.spotiutils.data.local.source

import android.content.Context

interface AuthCodeSource {
    fun saveAuthCode(code: String)
    fun getAuthCode(): String?
}