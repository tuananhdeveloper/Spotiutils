package com.tuananh.app.spotiutils.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: V
    protected abstract val bindingInflater: (LayoutInflater) -> V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    protected abstract fun initView()
    protected abstract fun initData()

}