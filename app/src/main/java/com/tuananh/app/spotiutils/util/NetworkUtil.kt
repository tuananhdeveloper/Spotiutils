package com.tuananh.app.spotiutils.util

import com.tuananh.app.spotiutils.BuildConfig
import java.net.HttpURLConnection
import java.net.URL


object NetworkUtil {
    const val GET_METHOD = "GET"
    const val POST_METHOD = "POST"
    const val CONTENT_TYPE = "Content-Type"
    const val JSON_TYPE = "application/json"
    const val BEARER = "Bearer"
    const val BASIC = "Basic"

    const val AUTHORIZATION = "Authorization"

    private fun initConnection(url: String, token: String): HttpURLConnection {
        val mUrl = URL(url)
        val urlConnection = mUrl.openConnection() as HttpURLConnection
        val myAuth = "$BEARER $token"
        urlConnection.setRequestProperty(AUTHORIZATION, myAuth)
        return urlConnection
    }

    fun requestToken(url: String): Response {
        val mUrl = URL(url)
        val urlConnection = mUrl.openConnection() as HttpURLConnection
        val token = android.util.Base64
            .encodeToString("${BuildConfig.CLIENT_ID}:${BuildConfig.CLIENT_SECRET}".toByteArray(),
                android.util.Base64.NO_WRAP)

        val myAuth = "$BASIC $token"
        urlConnection.setRequestProperty(AUTHORIZATION, myAuth)
        urlConnection.requestMethod = POST_METHOD
        return Response(urlConnection.responseCode,
            IOUtil.convertToStringBuilder(urlConnection.inputStream).toString())
    }

    fun performGetRequest(url: String, token: String): Response {
        val urlConnection = initConnection(url, token)
        urlConnection.requestMethod = GET_METHOD

        if(urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            return Response(urlConnection.responseCode,
                IOUtil.convertToStringBuilder(urlConnection.inputStream).toString())
        }
        return Response(urlConnection.responseCode,
            IOUtil.convertToStringBuilder(urlConnection.errorStream).toString())
    }

    fun performPostRequest(url: String, token: String, requestBody: String): Response {
        val urlConnection = initConnection(url, token)
        urlConnection.apply {
            requestMethod = POST_METHOD
            setRequestProperty(CONTENT_TYPE, JSON_TYPE)
        }

        IOUtil.writeData(requestBody, urlConnection.outputStream)

        if(urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            return Response(urlConnection.responseCode,
                IOUtil.convertToStringBuilder(urlConnection.inputStream).toString())
        }
        return Response(urlConnection.responseCode,
            IOUtil.convertToStringBuilder(urlConnection.errorStream).toString())
    }

}