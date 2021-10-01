package com.tuananh.app.spotiutils.ui.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.webkit.*
import android.widget.Toast
import com.tuananh.app.spotiutils.BuildConfig
import com.tuananh.app.spotiutils.base.BaseActivity
import com.tuananh.app.spotiutils.data.local.repository.AuthCodeRepository
import com.tuananh.app.spotiutils.data.local.repository.TokenLocalRepository
import com.tuananh.app.spotiutils.data.local.source.impl.AuthCodeSourceImpl
import com.tuananh.app.spotiutils.data.local.source.impl.TokenLocalSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.TokenDataSourceImpl
import com.tuananh.app.spotiutils.data.remote.repository.TokenRepository
import com.tuananh.app.spotiutils.databinding.ActivityWebviewBinding
import com.tuananh.app.spotiutils.ui.main.MainActivity
import java.lang.StringBuilder
import java.util.*

class WebViewActivity: BaseActivity<ActivityWebviewBinding>(), WebViewContract.View {

    private var myWebView: WebView? = null
    private var state: String? = null
    private var presenter: WebViewPresenter? = null

    override val bindingInflater: (LayoutInflater) -> ActivityWebviewBinding
        get() = ActivityWebviewBinding::inflate

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        myWebView = binding.myWebview
        val webSettings = myWebView!!.settings
        webSettings.javaScriptEnabled = true
        myWebView!!.webViewClient = MyWebViewClient()
        myWebView!!.loadUrl(buildUri().toString())
    }

    override fun initData() {
        val authCodeRepository = AuthCodeRepository.getInstance(
            AuthCodeSourceImpl.getInstance()
        )
        val tokenRepository = TokenRepository.getInstance(
            TokenDataSourceImpl.getInstance()
        )
        val tokenLocalRepository = TokenLocalRepository.getInstance(
            TokenLocalSourceImpl.getInstance()
        )
        presenter = WebViewPresenter(this, authCodeRepository, tokenRepository, tokenLocalRepository)

    }

    override fun showLoading() {
        binding.loading.show()
    }

    override fun hideLoading() {
        binding.loading.hide()
    }

    override fun navigate() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(this, getString(messageRes), Toast.LENGTH_LONG).show()
    }

    private fun buildUri(): Uri {
        state = UUID.randomUUID().toString()
        val uriBuilder = Uri.parse(AUTH_ENDPOINT).buildUpon()
            .appendQueryParameter(CLIENT_ID_REQUEST_PARAM, BuildConfig.CLIENT_ID)
            .appendQueryParameter(RESPONSE_TYPE_REQUEST_PARAM, RESPONSE_TYPE)
            .appendQueryParameter(REDIRECT_URI_REQUEST_PARAM, REDIRECT_URI)
            .appendQueryParameter(STATE_REQUEST_PARAM, state)

        val scopeStringBuilder = StringBuilder()
        for(item in SCOPES) {
            scopeStringBuilder.append("$item ")
        }
        
        uriBuilder.appendQueryParameter(SCOPE_REQUEST_PARAM, scopeStringBuilder.toString().trim())
        return uriBuilder.build()
    }

    private fun saveAuthCode(code: String)  {
        presenter?.saveAuthCode(code)
    }

    private fun requestToken() {
        presenter?.requestToken()
    }

    companion object {
        const val CLIENT_ID_REQUEST_PARAM = "client_id"
        const val RESPONSE_TYPE_REQUEST_PARAM = "response_type"
        const val REDIRECT_URI_REQUEST_PARAM = "redirect_uri"
        const val STATE_REQUEST_PARAM = "state"
        const val SCOPE_REQUEST_PARAM = "scope"
        const val CODE_REQUEST_PARAM = "code"

        const val AUTH_ENDPOINT = "https://accounts.spotify.com/authorize"
        const val RESPONSE_TYPE = "code"
        const val REDIRECT_URI = "http://localhost/callback"
        val SCOPES = listOf("user-read-private", "user-read-email", "user-read-recently-played")

        const val MSG_ACCESS_DENIED = "Access denied!"
    }

    inner class MyWebViewClient: WebViewClient() {

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            view?.loadUrl("about:blank")
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            hideLoading()
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

            request?.let {
                if(request.url.toString().startsWith(REDIRECT_URI)) {
                    //To prevent CSRF
                    val responseState = request.url.getQueryParameter(STATE_REQUEST_PARAM)
                    if(responseState == state) {

                        request.url.getQueryParameters(CODE_REQUEST_PARAM)?.let { code ->
                            if(code.size > 0) {
                                saveAuthCode(code[0])
                                requestToken()
                            }
                            else {
                                Toast.makeText(this@WebViewActivity, MSG_ACCESS_DENIED, Toast.LENGTH_LONG).show()
                                finish()
                            }
                        } ?: run {
                            Toast.makeText(this@WebViewActivity, MSG_ACCESS_DENIED, Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                }
            }
            return false
        }
    }
}