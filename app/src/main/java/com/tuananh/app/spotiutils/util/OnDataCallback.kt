package com.tuananh.app.spotiutils.util

interface OnDataCallback<O> {
    fun onResponse(data: O)
    fun onFailure(t: Throwable?)
}