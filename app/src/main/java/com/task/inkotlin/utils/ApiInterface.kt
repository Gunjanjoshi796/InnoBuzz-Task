package com.task.inkotlin.utils

import com.task.inkotlin.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(StringHelper.GET_POSTS)
    fun getPosts() : Call<ArrayList<Posts>>
}