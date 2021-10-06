package com.example.reto01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class activity_7admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_7admin)

        window.decorView.apply {
            //esconde el nav inferior
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        }
    }
}