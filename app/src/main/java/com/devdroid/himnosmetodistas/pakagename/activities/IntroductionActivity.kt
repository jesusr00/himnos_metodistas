package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import kotlinx.android.synthetic.main.introduction_activity.*

class IntroductionActivity: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val myPref = "mypref"
    private val isAlreadyOpen = "isalreadyopen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.introduction_activity)

        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE)

        btn.setOnClickListener {
            sharedPreferences
                .edit()
                .putBoolean(isAlreadyOpen, true)
                .apply()

            startActivity(Intent(this@IntroductionActivity, MainActivity::class.java))
        }
    }
}