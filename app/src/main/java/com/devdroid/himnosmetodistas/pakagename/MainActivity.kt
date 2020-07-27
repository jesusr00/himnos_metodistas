package com.devdroid.himnosmetodistas.pakagename

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devdroid.himnosmetodistas.pakagename.ui.hymnlist.HymnListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HymnListFragment::class.java, null)
                .commitNow()
        }
    }
}