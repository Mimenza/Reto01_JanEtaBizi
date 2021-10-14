package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import com.example.reto01.Adapter.MySliderImageAdapter

import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_4producto.*

import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.activity_7_01usuarios.*
import kotlinx.android.synthetic.main.activity_7_02productos.*
import java.util.ArrayList

class activity_7_02productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_02productos)
        getSupportActionBar()?.hide()

        val lista = arrayOf("PRODUCTO 1", "PRODUCTO 2", "PRODUCTO 3", "PRODUCTO 4","CREAR NUEVO PRODUCTO")


        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner_productos.adapter = adaptador

        //Image slider (Imagenes)

        val imageSlider = findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()

        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        setImageInSlider(imageList, imageSlider)

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
    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = false

    }
}