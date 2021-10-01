package com.tuananh.app.spotiutils.util

import org.json.JSONArray

inline fun <reified T> JSONArray.parseObjectArray() = JSONArray(this).run {
    when(T::class) {

    }
}