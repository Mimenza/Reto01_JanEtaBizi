package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_7_2_2producto.*

class activity_9_receta01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9_receta01)
        getSupportActionBar()?.hide()
        imgv7_02_atras.setOnClickListener() {
            val i = Intent(this, activity_9blog::class.java)
            startActivity(i)
        }
    }
}