package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        runBlocking {
            launch {
                Handler().postDelayed({
                    val intent = Intent(this@MainActivity, activity_1login::class.java)
                    startActivity(intent)
                    this@MainActivity.overridePendingTransition(0, 0)
                    finish()
                }, 2000) // 2000 is the delayed time in milliseconds.
            }
        }
    }
}
