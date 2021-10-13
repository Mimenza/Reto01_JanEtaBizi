package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_4producto.*

import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.activity_7_01usuarios.*
import kotlinx.android.synthetic.main.activity_7_02productos.*

class activity_7_02productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_02productos)
        getSupportActionBar()?.hide()

        val lista = arrayOf("PRODUCTO 1", "PRODUCTO 2", "PRODUCTO 3", "PRODUCTO 4","CREAR NUEVO PRODUCTO")


        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner_productos.adapter = adaptador

        imgv7_02_atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }





   /*     btn_7_01Eliminar.setOnTouchListener{  v, event ->

            btn_7_02save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border)
            }, 100)

            false

        }
        btn_7_02save.setOnTouchListener{  v, event ->
            btn_7_02save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            }, 100)

            false

        }*/


        //Producto  descripción scroll
        txtv_7_02descripcionproducto.movementMethod = ScrollingMovementMethod()

    }
}