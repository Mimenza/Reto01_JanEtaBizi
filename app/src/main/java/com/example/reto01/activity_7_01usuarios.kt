package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.activity_7_01usuarios.*

class activity_7_01usuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_01usuarios)
        getSupportActionBar()?.hide()
        val lista = arrayOf("USUARIO1", "USUARIO2", "USUARIO3", "USUARIO4")
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner_usuarios.adapter =adaptador

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