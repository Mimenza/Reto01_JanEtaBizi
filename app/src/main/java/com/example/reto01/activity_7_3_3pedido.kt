package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_7_3pedidos.*

class activity_7_3_3pedido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_7_3pedidos)

        imgv7_02_atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }
    }
}
