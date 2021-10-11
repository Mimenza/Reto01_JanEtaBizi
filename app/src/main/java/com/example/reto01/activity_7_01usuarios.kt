package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_7_01usuarios.*
import kotlinx.android.synthetic.main.activity_7_01usuarios.view.*



class activity_7_01usuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_01usuarios)
        getSupportActionBar()?.hide()

        imgv7_01_atras.setOnClickListener(){
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }
        imgv7_01_logout.setOnClickListener(){
            val i = Intent(this, activity_1login::class.java)
            startActivity(i)
        }
    }
}