package com.tuananh.app.spotiutils.data.local.repository

import com.tuananh.app.spotiutils.data.local.source.AuthCodeSource

class AuthCodeRepository private constructor(
    private val authCodeSource: AuthCodeSource
): AuthCodeSource {

    override fun saveAuthCode(code: String) {
        authCodeSource.saveAuthCode(code)
    }

    override fun getAuthCode(): String? {
        return authCodeSource.getAuthCode()
    }

    companion object {
        private var instance: AuthCodeRepository? = null

        fun getInstance(authCodeSource: AuthCodeSource) =
            instance ?: AuthCodeRepository(authCodeSource).also {
                instance = it
            }
    }
}