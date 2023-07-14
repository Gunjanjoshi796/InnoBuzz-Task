package com.task.inkotlin.utils

interface DataCallback<T> {

    fun onSuccess(t : T)
    fun onError(errorMessage : String)
}