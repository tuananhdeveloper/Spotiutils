package com.tuananh.app.spotiutils.ui.main

import android.view.LayoutInflater
import com.tuananh.app.spotiutils.base.BaseActivity
import com.tuananh.app.spotiutils.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initView() {

    }

    override fun initData() {
        return
    }
}