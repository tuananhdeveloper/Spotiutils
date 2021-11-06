package com.tuananh.app.spotiutils.ui.webview

import android.util.Log
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.local.repository.AuthCodeRepository
import com.tuananh.app.spotiutils.data.local.repository.TokenLocalRepository
import com.tuananh.app.spotiutils.data.remote.model.TokenData
import com.tuananh.app.spotiutils.data.remote.repository.TokenRepository
import com.tuananh.app.spotiutils.util.OnDataCallback

class WebViewPresenter(
    private val view: WebViewContract.View,
    private val repositoryAuthCode: AuthCodeRepository,
    private val tokenRepository: TokenRepository,
    private val tokenLocalRepository: TokenLocalRepository
): WebViewContract.Presenter {

    override fun saveAuthCode(code: String) {
        repositoryAuthCode.saveAuthCode(code)
    }

    override fun requestToken() {
        view.showLoading()
        tokenRepository.getToken(object: OnDataCallback<TokenData> {
            override fun onResponse(data: TokenData) {
                Log.d("my_token", data.toString())
                saveToken(data)
                view.hideLoading()
                view.navigate()
            }

            override fun onFailure(t: Throwable?) {
                view.showMessage(R.string.error_token_request)
            }
        })
    }

    private fun saveToken(data: TokenData) {
        tokenLocalRepository.saveToken(data)
        prepareData(data)
    }

    private fun prepareData(data: TokenData) {
        PreparedData.updateToken(data)
    }
}