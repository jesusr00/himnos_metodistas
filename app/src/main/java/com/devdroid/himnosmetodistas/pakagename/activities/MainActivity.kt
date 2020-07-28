package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.adapters.HymnListRecyclerviewAdapter
import com.devdroid.himnosmetodistas.pakagename.database.asynctasks.LoadHymnTitlesAsyncTask
import com.devdroid.himnosmetodistas.pakagename.listeners.GenericAsyncTaskListener
import com.devdroid.himnosmetodistas.pakagename.listeners.RecyclerviewAdapterListener
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import com.devdroid.himnosmetodistas.pakagename.fragments.HymnDetailsFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), GenericAsyncTaskListener<ArrayList<HymnTitle>>, RecyclerviewAdapterListener<HymnTitle> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        toolbar.title = title
        LoadHymnTitlesAsyncTask(this).execute()
    }

    override fun onTaskFinished(result: ArrayList<HymnTitle>) {
        hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(result, this)
    }



    override fun onClick(item: HymnTitle) {
        val intent = Intent(this, HymnDetailsActivity::class.java).apply {
            putExtra(HymnDetailsFragment.ID_ARG, item.id)
        }
        startActivity(intent)
    }

    override fun getContext(): Context? {
        return this
    }

}