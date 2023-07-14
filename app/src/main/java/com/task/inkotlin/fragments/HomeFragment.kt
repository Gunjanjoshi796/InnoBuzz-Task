package com.task.inkotlin.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.inkotlin.App
import com.task.inkotlin.MainActivity
import com.task.inkotlin.R
import com.task.inkotlin.adapter.PostsAdapter
import com.task.inkotlin.model.Posts
import com.task.inkotlin.utils.ApiManager
import com.task.inkotlin.utils.DataCallback
import com.task.inkotlin.utils.PostDatabase

class HomeFragment : Fragment(), PostsAdapter.PostsListener {

    lateinit var apiManager: ApiManager
    lateinit var postsRV : RecyclerView
    lateinit var postsAdapter : PostsAdapter
    lateinit var postDatabase: PostDatabase
    lateinit var whatsappBTN : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        inItWidgets(view)
        setupRecyclerView()
        whatsappBTN.setOnClickListener { openWhatsApp() }

        return view
    }

    private fun setupRecyclerView() {
        postsRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
    }

    private fun inItWidgets(view: View) {
        apiManager = App().getApiManager()
        whatsappBTN = view.findViewById(R.id.whatsapp_btn)
        postsAdapter = context?.let { PostsAdapter(it, this) }!!
        postsRV = view.findViewById(R.id.posts_rv)
        postDatabase = PostDatabase.getInstance(requireContext())
    }

    override fun onResume() {
        super.onResume()
        getPosts(postDatabase.postDao().getAllPosts().isEmpty())
    }

    private fun getPosts(dontHavePost : Boolean) {
        if (dontHavePost){
            apiManager.getPosts( object : DataCallback<ArrayList<Posts>> {
                override fun onSuccess(data: ArrayList<Posts>) {
                    setupData(data)
                }

                override fun onError(serverError: String) {
                    // Handle error here
                    Toast.makeText(context, serverError, Toast.LENGTH_SHORT).show()
                }
            })
        } else
            postsAdapter.setPosts(postDatabase.postDao().getAllPosts() as ArrayList<Posts>)
    }

    private fun setupData(data: ArrayList<Posts>) {
        postDatabase.postDao().savePosts(data)
        postsAdapter.setPosts(postDatabase.postDao().getAllPosts() as ArrayList<Posts>)
    }

    override fun postSelected(id: Int) {
        val mainActivity = activity as? MainActivity
        val fragmentManagerHelper = mainActivity?.fragmentManagerHelper
        fragmentManagerHelper!!.replace(DetailsFragment(id), true)

    }

    private fun openWhatsApp() {
        val intent = Intent(Intent.ACTION_VIEW)

        try {
            Toast.makeText(context,
                "Opening Whatsapp",
                Toast.LENGTH_SHORT)
                .show()
            val url = "https://api.whatsapp.com/send?text="
            intent.data = Uri.parse(url)
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            // WhatsApp is not installed on the device
            Toast.makeText(context,
            "Install Whatsapp First",
            Toast.LENGTH_SHORT)
                .show()
        }
    }

}