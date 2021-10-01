package com.tuananh.app.spotiutils.util

import android.os.AsyncTask
import android.util.Log

class MyAsyncTask<I, O>(
    private val callback: OnDataCallback<O>,
    private val handler: (I) -> O?
): AsyncTask<I, Unit,O?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: I): O? =
        try {
            handler(params[0])
        }
        catch (e: Exception) {
            exception = e
            e.message?.let { Log.e("my_error", it) }
            null
        }

    override fun onPostExecute(result: O?) {
        super.onPostExecute(result)
        result?.let {
            callback.onResponse(it)
        } ?: callback.onFailure(exception)
    }

}