package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_9_receta02.*

class activity_9_receta02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9_receta02)
        getSupportActionBar()?.hide()
        imgv9_03_atras.setOnClickListener() {
            val i = Intent(this, activity_9blog::class.java)
            startActivity(i)
        }
    }
}