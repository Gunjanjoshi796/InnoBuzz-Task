package com.task.inkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import com.task.inkotlin.fragments.HomeFragment
import com.task.inkotlin.utils.FragmentManagerHelper
import com.task.inkotlin.utils.PostDatabase

class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager
    lateinit var fragmentManagerHelper : FragmentManagerHelper
    lateinit var postDatabase: PostDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inItWidgets()
        postDatabase.postDao().clearPosts()
        fragmentManagerHelper.replace(HomeFragment(), false)
    }

    private fun inItWidgets() {
        fragmentManagerHelper = FragmentManagerHelper(fragmentManager)
        postDatabase = PostDatabase.getInstance(this)
    }

}