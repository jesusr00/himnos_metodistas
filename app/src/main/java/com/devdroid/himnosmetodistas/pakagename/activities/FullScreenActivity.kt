package com.devdroid.himnosmetodistas.pakagename.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.himnosmetodistas.pakagename.R
import kotlinx.android.synthetic.main.fullscreen_activity.*
import java.util.*
import kotlin.concurrent.schedule

class FullScreenActivity: AppCompatActivity() {

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

        Timer().schedule( 4000){
            val intent = Intent(this@FullScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}