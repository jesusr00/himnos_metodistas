package com.devdroid.himnosmetodistas.pakagename.ui.hymndetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.database.DatabaseHelper
import com.devdroid.himnosmetodistas.pakagename.models.Hymn
import kotlinx.android.synthetic.main.hymn_details.*

class HymnDetailsFragment: Fragment() {

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

            hymnDetailNum.text = hymn.num.toString()
            hymnDetailTitle.text = hymn.title
            hymnDetailText.text = hymn.text
            if (hymn.favorite == "true"){
                rate.setImageResource(R.drawable.ic_yelow_star_24)
            }else{
                rate.setImageResource(R.drawable.ic_white_star_24)
            }

            rate.setOnClickListener {
                db.toggleIsHymnFavorite(hymn.id)
                onViewCreated(requireView(), null)
            }
        }
    }

    companion object{
        const val ID_ARG = "ID"
    }
}