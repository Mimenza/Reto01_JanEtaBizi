package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.thesimplycoder.simpleintroslider.IntroSliderActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getSupportActionBar()?.hide()

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.

        Handler().postDelayed({
            val intent = Intent(this, IntroSliderActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 is the delayed time in milliseconds.
    }
    }
