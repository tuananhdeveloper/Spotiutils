package com.tuananh.app.spotiutils.base

import androidx.annotation.StringRes

interface BaseView {
    fun showMessage(@StringRes messageRes: Int)
}