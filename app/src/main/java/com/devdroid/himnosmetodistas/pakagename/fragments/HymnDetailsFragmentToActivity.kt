package com.devdroid.himnosmetodistas.pakagename.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.database.DatabaseHelper
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.hymn_details.*
import kotlinx.android.synthetic.main.hymn_details_activity.*
import kotlinx.android.synthetic.main.main_activity.*

class HymnDetailsFragmentToActivity: Fragment() {

    private var hymnId: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            hymnId = getInt(ID_ARG, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hymn_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context!!.apply {
            val db = DatabaseHelper(this)
            val hymn = db.selectHymnById(hymnId!!)
            val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
            val toolbarLayout = activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)

            toolbarLayout?.title ="${hymn.num}: ${hymn.title}"
            hymnDetailText.text = hymn.text
            if (hymn.favorite == "true"){
                fab?.setImageResource(R.drawable.ic_yelow_star_24)
            }else{
                fab?.setImageResource(R.drawable.ic_white_star_24)

            }

            fab?.setOnClickListener {
                db.toggleIsHymnFavorite(hymn.id)
                onViewCreated(requireView(), null)
            }
        }
    }

    companion object{
        const val ID_ARG = "ID"
    }
}