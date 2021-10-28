package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_9_receta01.*
import kotlinx.android.synthetic.main.activity_9_receta02.*
import kotlinx.android.synthetic.main.activity_9_receta03.*
import java.io.File

class activity_9_receta03 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9_receta03)
        getSupportActionBar()?.hide()
        imgv9_03atras.setOnClickListener() {
            val i = Intent(this, activity_9blog::class.java)
            startActivity(i)
        }

        loadCarritoNumber()

        sv_9_03scrollView.setVerticalScrollBarEnabled(false)

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

         bottomNavV_9_3bottomMenu.setSelectedItemId(R.id.navigation_blog)

        bottomNavV_9_3bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

    fun loadCarritoNumber() {
        if (File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()) {
            var badge = bottomNavV_9_3bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)

            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length", null).toString().toInt()
        }
    }
}