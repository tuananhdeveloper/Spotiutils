package com.tuananh.app.spotiutils.ui.login

import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseActivity
import com.tuananh.app.spotiutils.databinding.ActivityLoginBinding
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity
import com.tuananh.app.spotiutils.util.show
import com.tuananh.app.spotiutils.util.hide

class LoginActivity : BaseActivity<ActivityLoginBinding>(), View.OnClickListener {

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun initView() {
        binding.btnLogin.setOnClickListener(this)
    }

    override fun initData() {
        return
    }

    override fun onClick(v: View?) {
        binding.progressIndicator.visibility = View.VISIBLE
        openWebView()
        binding.progressIndicator.visibility = View.GONE
    }

    private fun openWebView() {
        startActivityForResult(Intent(this, WebViewActivity::class.java), REQUEST_CODE)
    }

    companion object {
        const val REQUEST_CODE = 100
    }

}