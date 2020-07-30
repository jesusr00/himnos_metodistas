package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.adapters.HymnListRecyclerviewAdapter
import com.devdroid.himnosmetodistas.pakagename.database.asynctasks.LoadHymnTitlesAsyncTask
import com.devdroid.himnosmetodistas.pakagename.listeners.GenericAsyncTaskListener
import com.devdroid.himnosmetodistas.pakagename.listeners.RecyclerviewAdapterListener
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import com.devdroid.himnosmetodistas.pakagename.fragments.HymnDetailsFragmentToActivity
import kotlinx.android.synthetic.main.hymn_list_recyclerview.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), GenericAsyncTaskListener<ArrayList<HymnTitle>>, RecyclerviewAdapterListener<HymnTitle> {

    private lateinit var list: ArrayList<HymnTitle>
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        toolbar.title = title
        twoPane = findViewById<NestedScrollView>(R.id.itemDetailContainer) != null
        LoadHymnTitlesAsyncTask(this).execute()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val menuSearch = menu?.findItem(R.id.menuSearch)?.actionView as SearchView
        menuSearch.queryHint = "Buscar"
        menuSearch.setOnQueryTextListener(searchLogic)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onTaskFinished(result: ArrayList<HymnTitle>) {
        list = result
        hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(result, this)
    }

    override fun getContext(): Context? = this

    override fun onClick(item: HymnTitle) {
        if (twoPane){
            val bundle: Bundle = bundleOf(HymnDetailsFragmentToActivity.ID_ARG to item.id)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.itemDetailContainer, HymnDetailsFragmentToActivity::class.java, bundle, null)
                .commit()
        }else{
            val intent = Intent(this, HymnDetailsActivity::class.java).apply {
                putExtra(HymnDetailsFragmentToActivity.ID_ARG, item.id)
            }
            startActivity(intent)
        }

    }

    private val searchLogic = object : SearchView.OnQueryTextListener{
        var displayList = ArrayList<HymnTitle>()
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (query!!.isNotEmpty()){
                displayList.clear()
                for (hymnTitle in list) {
                    if (hymnTitle.title.toLowerCase(Locale.ROOT).contains(query.toString().toLowerCase(Locale.ROOT)) ||
                        hymnTitle.num.toString().toLowerCase(Locale.ROOT).contains(query.toString().toLowerCase(Locale.ROOT))){
                        displayList.add(hymnTitle)
                    }
                }

                hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(displayList, this@MainActivity)
            }else{
                hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(list, this@MainActivity)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText!!.isNotEmpty()){
                displayList.clear()
                for (hymnTitle in list) {
                    if (hymnTitle.title.toLowerCase(Locale.ROOT).contains(newText.toString().toLowerCase(Locale.ROOT)) ||
                        hymnTitle.num.toString().toLowerCase(Locale.ROOT).contains(newText.toString().toLowerCase(Locale.ROOT))){
                        displayList.add(hymnTitle)
                    }
                }

                hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(displayList, this@MainActivity)
            }else{
                hymnListRecyclerview.adapter = HymnListRecyclerviewAdapter(list, this@MainActivity)
            }
            return true
        }

    }
}