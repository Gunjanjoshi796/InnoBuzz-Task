package com.task.inkotlin.utils

import com.task.inkotlin.model.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManagerImpl : ApiManager {
    override fun getPosts(callback: DataCallback<ArrayList<Posts>>) {
        ApiUtilities().getApiInterface().getPosts().enqueue(object : Callback<ArrayList<Posts>> {
            override fun onResponse(
                call: Call<ArrayList<Posts>>,
                response: Response<ArrayList<Posts>>
            ) {
                if (response.isSuccessful) {
                    // Handle the successful response here
                    if (response.body() != null) {
                        if (response!!.body()!!.size > 0) {
                            callback.onSuccess(response.body()!!)
                        } else callback.onError("Something's not right")
                    } else callback.onError("Something's not right")
                } else
                    callback.onError("Something's not right")
            }

            override fun onFailure(call: Call<ArrayList<Posts>>, t: Throwable) {
                // Handle the failure here
                callback.onError("Something's not right")
            }
        })
    }

}