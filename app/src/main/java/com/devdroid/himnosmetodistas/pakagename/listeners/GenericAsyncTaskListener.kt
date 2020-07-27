package com.devdroid.himnosmetodistas.pakagename.listeners

interface GenericAsyncTaskListener<T>: AsyncTaskListener {
    fun onTaskFinished(result: T)
}