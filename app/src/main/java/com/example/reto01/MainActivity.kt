package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this, activity_1login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0)
            finish()
        }, 2000) // 2000 is the delayed time in milliseconds.
    }
}