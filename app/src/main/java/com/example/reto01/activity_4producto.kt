package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.site_valley.imagesliderexampleinkotlin.MySliderImageAdapter


import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5carrito.*
import kotlinx.android.synthetic.main.activity_6usuario.*

import java.util.*


class activity_4producto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_4producto)

        bottomNavV_4bottomMenu.setSelectedItemId(R.id.navigation_invisible)

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

        imageList.add("https://s03.s3c.es/imag/_v0/770x420/d/c/5/risitas-operado.jpg")
        imageList.add("https://www.huelvahoy.com/wp-content/uploads/2020/08/el-risitas-800x400.jpg")
        imageList.add("https://s1.eestatic.com/2020/09/14/bluper/bluper_520710891_169287780_1024x576.jpg")
        setImageInSlider(imageList, imageSlider)
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = false

    }
    }
