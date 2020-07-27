package com.devdroid.himnosmetodistas.pakagename.listeners

interface RecyclerviewAdapterListener<T>: AsyncTaskListener {
    fun onClick(item: T)
}