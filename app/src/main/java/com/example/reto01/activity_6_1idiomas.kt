package com.example.reto01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_6_1idiomas.*

class activity_6_1idiomas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_6_1idiomas)

        imgv_6_2back.setOnClickListener {
            val i = Intent(this, R.layout.activity_6usuario::class.java)
            startActivity(i)
        }
    }
}