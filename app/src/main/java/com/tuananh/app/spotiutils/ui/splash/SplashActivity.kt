package com.tuananh.app.spotiutils.ui.splash

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.local.repository.TokenLocalRepository
import com.tuananh.app.spotiutils.data.local.source.impl.AuthCodeSourceImpl
import com.tuananh.app.spotiutils.data.local.source.impl.TokenLocalSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.TokenDataSourceImpl
import com.tuananh.app.spotiutils.ui.App
import com.tuananh.app.spotiutils.ui.main.MainActivity
import com.tuananh.app.spotiutils.ui.login.LoginActivity
import java.io.File

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!isConnected()) {
            showCustomDialog()
        }
        else {
            if(!isUserLoggedIn()) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else {
                prepareData()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun prepareData() {
        val token = TokenLocalRepository.getInstance(
            TokenLocalSourceImpl.getInstance()
        ).readToken()

        PreparedData.updateToken(token)
    }

    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_connection_dialog))
            .setMessage(getString(R.string.msg_connection_dialog))
            .setCancelable(false)
            .setNegativeButton(R.string.text_dismiss_button_connection_dialog) { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun isConnected(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if(networkInfo != null && networkInfo.isConnected) {
            return true
        }
        return false
    }

    private fun isUserLoggedIn(): Boolean {
        App.getAppContext()?.let {
            val authCodeFile = File(it.filesDir, AuthCodeSourceImpl.FILE_NAME)
            if(authCodeFile.exists()) return true
        }
        return false
    }

}