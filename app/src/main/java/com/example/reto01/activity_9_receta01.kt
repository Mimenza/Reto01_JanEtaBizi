package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_7_2_2producto.*
import kotlinx.android.synthetic.main.activity_9blog.*

class activity_9_receta01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9_receta01)
        getSupportActionBar()?.hide()
        imgv7_02_atras.setOnClickListener() {
            val i = Intent(this, activity_9blog::class.java)
            startActivity(i)
        }
        bottomNavV_9bottomMenu.setSelectedItemId(R.id.navigation_blog)
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

        fun navegacion_blog() {
            val navegacion_blog = Intent(this, activity_9blog::class.java)
            startActivity(navegacion_blog)
            this.overridePendingTransition(0, 0)

        }

        bottomNavV_9bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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
                R.id.navigation_blog -> {
                    navegacion_blog()
                    true
                }
                else -> false
            }
        }




    }
}