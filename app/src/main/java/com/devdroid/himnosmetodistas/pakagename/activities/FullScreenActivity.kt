package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import kotlinx.android.synthetic.main.fullscreen_activity.*

class FullScreenActivity: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val myPref = "mypref"
    private val isAlreadyOpen = "isalreadyopen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_activity)

        fullScreenLayout.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fullScreenLayout.animation = animation

        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE)

        val intent: Intent = if (sharedPreferences.contains(isAlreadyOpen)){
            if (sharedPreferences.getBoolean(isAlreadyOpen, false)){
                Intent(this@FullScreenActivity, MainActivity::class.java)
            }else {
                Intent(this@FullScreenActivity, IntroductionActivity::class.java)
            }
        }else{
            Intent(this@FullScreenActivity, IntroductionActivity::class.java)
        }

        val showHandler = Handler()
        val showRunnable = Runnable {
            startActivity(intent)
        }

        showHandler.postDelayed(showRunnable, 4000)
    }

}