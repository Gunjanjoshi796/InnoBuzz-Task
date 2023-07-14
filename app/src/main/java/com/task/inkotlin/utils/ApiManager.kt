package com.task.inkotlin.utils

import com.task.inkotlin.model.Posts

interface ApiManager {
    fun getPosts(callback: DataCallback<ArrayList<Posts>>)
}