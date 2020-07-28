package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.fragments.HymnDetailsFragment
import kotlinx.android.synthetic.main.hymn_details_activity.*

class HymnDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hymn_details_activity)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null){
            val fragment = HymnDetailsFragment()
                .apply {
                arguments = Bundle().apply {
                    putInt(
                        HymnDetailsFragment.ID_ARG, intent.getIntExtra(
                            HymnDetailsFragment.ID_ARG, 0))
                }
            }

            supportFragmentManager
                .beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?) =
        when(item?.itemId){
            android.R.id.home -> {
                navigateUpTo(Intent(this, MainActivity::class.java))
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    
}