package com.tuananh.app.spotiutils.util

import android.os.AsyncTask

class MyAsyncTask<I, O>(
    private val callback: OnDataCallback<O>,
    private val handler: (I?) -> O?
): AsyncTask<I, Unit,O?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: I): O? =
        try {
            if(params.isNotEmpty()) {
                handler(params[0])
            }
            else {
                handler(null)
            }
        }
        catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: O?) {
        super.onPostExecute(result)
        result?.let {
            callback.onResponse(it)
        } ?: callback.onFailure(exception)
    }

}