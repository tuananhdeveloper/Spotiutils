package com.tuananh.app.spotiutils.ui.webview

import com.tuananh.app.spotiutils.base.BaseView

interface WebViewContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun navigate()
    }

    interface Presenter {
        fun saveAuthCode(code: String)
        fun requestToken()
    }
}