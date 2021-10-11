package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_7_02productos.*

class activity_7_02productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_02productos)
        getSupportActionBar()?.hide()
        val lista = arrayOf("PRODUCTO 1", "PRODUCTO 2", "PRODUCTO 3", "PRODUCTO 4")
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner_productos.adapter = adaptador

        imgv7_02_atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }
        imgv7_02_logout.setOnClickListener() {
            val i = Intent(this, activity_1login::class.java)


        }
    }
}