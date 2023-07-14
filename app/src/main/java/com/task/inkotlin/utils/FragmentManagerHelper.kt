package com.task.inkotlin.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.task.inkotlin.R

class FragmentManagerHelper(private val fragmentManager: FragmentManager) {

    fun replace(fragment: Fragment, addToBackStack: Boolean) {
        replace(R.id.container, fragment, addToBackStack)
    }

    fun replace(@IdRes containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val replaceTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        replaceTransaction.replace(containerId, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            replaceTransaction.addToBackStack(fragment.javaClass.name)
        }
        replaceTransaction.commit()
    }
}