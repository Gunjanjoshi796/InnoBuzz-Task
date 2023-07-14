package com.task.inkotlin.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiUtilities {
    private var retrofit: Retrofit? = null

    fun getApiInterface(): ApiInterface {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(StringHelper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }
}