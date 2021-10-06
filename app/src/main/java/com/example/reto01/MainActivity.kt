package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        window.decorView.apply {
            //esconde el nav inferior
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        }

        btn_0login.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_1login::class.java)
            startActivity(i)
        }
        btn_0register.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_2registrar::class.java)
            startActivity(i)
        }
        btn_0principal.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_3principal::class.java)
            startActivity(i)
        }
        btn_0producto.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_4producto::class.java)
            startActivity(i)
        }
        btn_0carrito.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_5carrito::class.java)
            startActivity(i)
        }
        btn_0usuario.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_6usuario::class.java)
            startActivity(i)
        }
        btn_0admin.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_7admin::class.java)
            startActivity(i)
        }
        btn_0likes.setOnClickListener(){
            val i = Intent(this@MainActivity, activity_8likes::class.java)
            startActivity(i)
        }
    }
}