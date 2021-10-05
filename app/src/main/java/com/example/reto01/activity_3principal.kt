package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class activity_3principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_3principal)

        fun navegacion_compra() {
            val navegacion_compra = Intent(this, activity_5carrito::class.java)
            startActivity(navegacion_compra)

        }
        fun navegacion_likes() {
            val navegacion_likes = Intent(this, activity_3principal::class.java)
            startActivity(navegacion_likes)

        }
        fun navegacion_perfil() {
            val navegacion_perfil = Intent(this, activity_3principal::class.java)
            startActivity(navegacion_perfil)

        }
        fun navegacion_usuario() {
            val navegacion_usuario = Intent(this, activity_6usuario::class.java)
            startActivity(navegacion_usuario)

        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle item selection
            return when (item.itemId) {
                R.id.navigation_compra -> {
                    navegacion_compra()
                    true
                }
                R.id.navigation_likes -> {
                    navegacion_likes()
                    true
                }
                R.id.navigation_principal -> {
                    navegacion_perfil()
                    true
                }

                R.id.navigation_perfil -> {
                    navegacion_usuario()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }

    }
}