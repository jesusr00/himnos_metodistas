package com.devdroid.himnosmetodistas.pakagename.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devdroid.himnosmetodistas.pakagename.R

class HymnDetailsFragment: Fragment() {
    private var hymnId: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            hymnId = getInt(HymnDetailsFragmentToActivity.ID_ARG, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hymn_details, container, false)
    }

}