package com.tuananh.app.spotiutils.data.local.source.impl

import com.tuananh.app.spotiutils.data.local.source.AuthCodeSource
import com.tuananh.app.spotiutils.util.MyEncryptedSharedPrefs

class AuthCodeSourceImpl private constructor(): AuthCodeSource {

    override fun saveAuthCode(code: String) {
        MyEncryptedSharedPrefs.writeFile(FILE_NAME, code)
    }

    override fun getAuthCode(): String? {
        return MyEncryptedSharedPrefs.readFile(FILE_NAME) as String?
    }

    companion object {
        private var instance: AuthCodeSourceImpl? = null
        const val FILE_NAME = "auth_code.txt"

        fun getInstance() =
            instance ?: AuthCodeSourceImpl().also { instance = it }
    }
}