package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2registrar.*

class activity_2registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_2registrar)

        txtv_2Login.setOnClickListener(){

            val i = Intent(this@activity_2registrar, activity_1login::class.java)
            startActivity(i)
        }
    }
}