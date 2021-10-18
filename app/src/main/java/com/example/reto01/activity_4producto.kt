package com.example.reto01

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import com.example.reto01.Adapter.MySliderImageAdapter
import com.google.android.material.snackbar.Snackbar


import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_6usuario.*

import java.util.*


class activity_4producto : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_4producto)

        //bottomNavV_4bottomMenu.setSelectedItemId(R.id.navigation_invisible)

        fun navegacion_principal() {
            val navegacion_compra = Intent(this, activity_3principal::class.java)
            startActivity(navegacion_compra)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_likes() {
            val navegacion_likes = Intent(this, activity_8likes::class.java)
            startActivity(navegacion_likes)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_carrito() {
            val navegacion_perfil = Intent(this, activity_5carrito::class.java)
            startActivity(navegacion_perfil)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_perfil() {
            val navegacion_usuario = Intent(this, activity_6usuario::class.java)
            startActivity(navegacion_usuario)
            this.overridePendingTransition(0, 0)

        }

        bottomNavV_4bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_principal -> {
                    navegacion_principal()
                    true
                }
                R.id.navigation_likes -> {
                    navegacion_likes()
                    true
                }
                R.id.navigation_carrito -> {
                    navegacion_carrito()
                    true
                }
                R.id.navigation_perfil -> {
                    navegacion_perfil()
                    true
                }
                else -> false
            }
        }


        //Image slider (Imagenes)

        val imageSlider = findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()

        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        imageList.add("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg")
        setImageInSlider(imageList, imageSlider)

        var num = 0

        imgv_4menos.setOnClickListener(){

            if(num ==0){

                txt_4cantidad.text="0"
            }else{
                num--
                txt_4cantidad.text=num.toString()
            }

        }
        imgv_4mas.setOnClickListener(){

            num++

            txt_4cantidad.text=num.toString()
        }


        btn_4añadircarrito.setOnTouchListener{ v, event ->
            btn_4añadircarrito.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_4añadircarrito.setBackgroundResource(R.drawable.my_button_border)
            }, 100)

            false

        }

        btn_4comprar.setOnTouchListener{ v, event ->
            btn_4comprar.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_4comprar.setBackgroundResource(R.drawable.my_button_border)
            }, 100)

            false

        }

        imgv_4atras.setOnClickListener(){

            val i = Intent(this, activity_3principal::class.java)
            startActivity(i)
        }

         imgv4_favorite.setOnClickListener(){
             //Producto Favorito snackbar

             Snackbar.make(imgv4_favorite, "Producto añadido", Snackbar.LENGTH_SHORT)
                 .show()
         }


        //Producto  descripción scroll
        txtv_4descripcionproducto.movementMethod = ScrollingMovementMethod()


    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = false

    }

    }


