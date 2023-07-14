package com.task.inkotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.task.inkotlin.R
import com.task.inkotlin.model.Posts
import com.task.inkotlin.utils.PostDatabase

class DetailsFragment(id: Int) : Fragment() {

    var postID : Int = -1
    lateinit var headTV : TextView
    lateinit var detailTV : TextView

    init {
       postID = id
    }

    lateinit var postDatabase: PostDatabase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        inItWidgets(view)
        setupData(postDatabase.postDao().getPostByID(postID))
        return view
    }

    private fun setupData(postByID: Posts) {
        if (postByID != null){
            headTV.text = postByID.title
            detailTV.text = postByID.body
        }
    }

    private fun inItWidgets(view: View) {
        headTV = view.findViewById(R.id.head_tv)
        detailTV = view.findViewById(R.id.detail_tv)
        postDatabase = PostDatabase.getInstance(requireContext())
    }
}