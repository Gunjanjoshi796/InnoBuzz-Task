package com.task.inkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.inkotlin.R
import com.task.inkotlin.model.Posts

class PostsAdapter(private val context: Context, private val listener : PostsListener) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    lateinit var postsList : ArrayList<Posts>
    lateinit var mListener : PostsListener
    init {
        postsList = ArrayList()
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_posts, parent, false))
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        var post : Posts = postsList[position]
        holder.titleTV.text = post.title

        holder.itemView.setOnClickListener { mListener.postSelected(post.id) }

    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV : TextView = itemView.findViewById(R.id.post_title)
    }

    fun setPosts(list : ArrayList<Posts>){
        this.postsList.addAll(list)
        notifyDataSetChanged()
    }

    interface PostsListener{
        fun postSelected(id : Int)
    }

}