package com.tuananh.app.spotiutils.data.local.source.impl

import com.tuananh.app.spotiutils.data.local.source.TokenLocalSource
import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.data.remote.source.impl.TokenDataSourceImpl
import com.tuananh.app.spotiutils.util.MyEncryptedSharedPrefs

class TokenLocalSourceImpl private constructor(): TokenLocalSource {
    
    override fun saveToken(tokenData: TokenData) {
        MyEncryptedSharedPrefs.writeFile(TOKEN_DATA_FILE_NAME, tokenData)
    }

    override fun readToken(): TokenData {
        return MyEncryptedSharedPrefs.readFile(TOKEN_DATA_FILE_NAME) as TokenData
    }

    companion object {
        private var instance: TokenLocalSourceImpl? = null
        const val TOKEN_DATA_FILE_NAME = "token.txt"

        fun getInstance() = instance ?: TokenLocalSourceImpl().also { instance = it }

    }
}