package com.tuananh.app.spotiutils.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.addFragment(containerLayoutId: Int, fragment: Fragment, tag: String?, addToBackStack: Boolean) {
    if (addToBackStack) {
        beginTransaction().add(containerLayoutId, fragment, tag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }
    else {
        beginTransaction().add(containerLayoutId, fragment, tag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}

fun FragmentManager.replaceFragment(containerLayoutId: Int, fragment: Fragment, tag: String?, addToBackStack: Boolean) {
    if(addToBackStack) {
        beginTransaction().replace(containerLayoutId, fragment, tag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }
    else {
        beginTransaction().replace(containerLayoutId, fragment, tag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}

fun FragmentManager.removeFragment(fragment: Fragment) {
    beginTransaction().remove(fragment)
        .commit()
}
