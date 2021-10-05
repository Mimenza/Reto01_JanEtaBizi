package com.example.reto01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class activity_4producto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_4producto)
    }
}