package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import kotlinx.android.synthetic.main.fullscreen_activity.*
import kotlinx.android.synthetic.main.introduction_activity.*

class IntroductionActivity: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val myPref = "mypref"
    private val isAlreadyOpen = "isalreadyopen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.introduction_activity)

        introductionLayout.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE)

        btn.setOnClickListener {
//            sharedPreferences
//                .edit()
//                .putBoolean(isAlreadyOpen, true)
//                .apply()

            startActivity(Intent(this@IntroductionActivity, MainActivity::class.java))
        }

        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        introductionLayout.animation = animation
    }
}