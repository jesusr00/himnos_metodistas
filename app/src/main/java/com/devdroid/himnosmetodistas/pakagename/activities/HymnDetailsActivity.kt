package com.devdroid.himnosmetodistas.pakagename.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.fragments.HymnDetailsFragmentToActivity
import kotlinx.android.synthetic.main.hymn_details_activity.*

class HymnDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hymn_details_activity)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null){
            val fragment = HymnDetailsFragmentToActivity()
                .apply {
                arguments = Bundle().apply {
                    putInt(
                        HymnDetailsFragmentToActivity.ID_ARG, intent.getIntExtra(
                            HymnDetailsFragmentToActivity.ID_ARG, 0))
                }
            }

            supportFragmentManager
                .beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

}