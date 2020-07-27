package com.devdroid.himnosmetodistas.pakagename.ui.hymnlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.adapters.HymnListRecyclerviewAdapter
import com.devdroid.himnosmetodistas.pakagename.database.asynctasks.LoadHymnTitlesAsyncTask
import com.devdroid.himnosmetodistas.pakagename.listeners.GenericAsyncTaskListener
import com.devdroid.himnosmetodistas.pakagename.listeners.RecyclerviewAdapterListener
import com.devdroid.himnosmetodistas.pakagename.models.Hymn
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import com.devdroid.himnosmetodistas.pakagename.ui.hymndetails.HymnDetailsFragment
import kotlinx.android.synthetic.main.hymn_list_fragment.*

class HymnListFragment : Fragment(), GenericAsyncTaskListener<ArrayList<HymnTitle>>, RecyclerviewAdapterListener<HymnTitle> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.hymn_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoadHymnTitlesAsyncTask(this).execute()
    }

    override fun onTaskFinished(result: ArrayList<HymnTitle>) {
     hymnRecyclerview.adapter = HymnListRecyclerviewAdapter(result, this@HymnListFragment)
    }

    override fun onClick(item: HymnTitle) {
        val bundle: Bundle = bundleOf(HymnDetailsFragment.ID_ARG to item.id)
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, HymnDetailsFragment::class.java, bundle, null)
            .commit()
    }


}