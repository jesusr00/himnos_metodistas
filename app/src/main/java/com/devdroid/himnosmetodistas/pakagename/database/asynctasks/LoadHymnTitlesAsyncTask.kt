package com.devdroid.himnosmetodistas.pakagename.database.asynctasks

import android.content.Context
import android.os.AsyncTask
import com.devdroid.himnosmetodistas.pakagename.database.DatabaseHelper
import com.devdroid.himnosmetodistas.pakagename.listeners.GenericAsyncTaskListener
import com.devdroid.himnosmetodistas.pakagename.models.Hymn
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import javax.xml.transform.ErrorListener

class LoadHymnTitlesAsyncTask(private val listener: GenericAsyncTaskListener<ArrayList<HymnTitle>>) : AsyncTask<Int, Void, ArrayList<HymnTitle>>() {

    private lateinit var db: DatabaseHelper

    override fun onPreExecute() {
        super.onPreExecute()
        if (listener.getContext() != null){
            db = DatabaseHelper(listener.getContext() as Context)
        }
    }

    override fun doInBackground(vararg params: Int?): ArrayList<HymnTitle> {
        return db.selectAllTitles()
    }

    override fun onPostExecute(result: ArrayList<HymnTitle>?) {
        super.onPostExecute(result)
        result?.apply {
            listener.onTaskFinished(this)
        }
    }
}