package com.task.inkotlin

import android.app.Application
import com.task.inkotlin.utils.ApiManager
import com.task.inkotlin.utils.ApiManagerImpl

class App :Application() {

    lateinit var apiManager: ApiManagerImpl

    fun getApiManager(): ApiManager {
        if (!::apiManager.isInitialized) {
            apiManager = ApiManagerImpl()
        }
        return apiManager
    }

}