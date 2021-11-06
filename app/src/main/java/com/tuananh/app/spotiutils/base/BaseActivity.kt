package com.tuananh.app.spotiutils.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.tuananh.app.spotiutils.util.replaceFragment

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

    protected fun openFragment(layoutContainerId: Int, fragment: Fragment) {
        supportFragmentManager.replaceFragment(layoutContainerId,
            fragment,
            null,
            addToBackStack = false)
    }

    protected abstract fun initView()
    protected abstract fun initData()


}