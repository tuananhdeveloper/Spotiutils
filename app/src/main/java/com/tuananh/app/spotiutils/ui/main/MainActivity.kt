package com.tuananh.app.spotiutils.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseActivity
import com.tuananh.app.spotiutils.databinding.ActivityMainBinding
import com.tuananh.app.spotiutils.ui.overview.OverviewFragment
import com.tuananh.app.spotiutils.ui.profile.ProfileFragment
import com.tuananh.app.spotiutils.ui.top.TopFragment

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationBarView.OnItemSelectedListener {

    private var lastFragment: Fragment? = null

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initView() {
        with(binding.bottomNavigation) {
            setOnItemSelectedListener(this@MainActivity)
            selectedItemId = R.id.menu_overview
        }
    }

    override fun initData() {
        return
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        while(supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStackImmediate()
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.menu_overview -> selectedFragment = OverviewFragment()
            R.id.menu_top -> selectedFragment = TopFragment()
            R.id.menu_profile -> selectedFragment = ProfileFragment()
        }
        if(selectedFragment != null) {
            if(lastFragment != null) {
                if(lastFragment!!::class != selectedFragment::class) {
                    openFragment(R.id.fragment_container, selectedFragment)
                }
                else {
                    Log.i(TAG, MSG_SAME_FRAGMENT)
                }
            }
            else {
                openFragment(R.id.fragment_container, selectedFragment)
            }
        }
        else {
            Log.i(TAG, MSG_NO_FRAGMENT)
        }

        lastFragment = selectedFragment
        return true
    }

    companion object {
        val TAG = MainActivity::class.simpleName
        const val MSG_SAME_FRAGMENT = "You selected the same fragment"
        const val MSG_NO_FRAGMENT = "No fragment selected"
    }
}