package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5carrito.*

class activity_5_2payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5_2payment)
        getSupportActionBar()?.hide()

        imgv_5_2atras.setOnClickListener(){
            val i = Intent(this@activity_5_2payment, activity_5_1adress::class.java)
            startActivity(i)
        }

        btn_5_2payment.setOnTouchListener{ v, event ->
            btn_5_2payment.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_2payment.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }
        btn_5_2payment.setOnClickListener(){

            val i = Intent(this@activity_5_2payment, activity_5_3gracias::class.java)
            startActivity(i)
        }

        bottomNavV_5_2bottomMenu.setSelectedItemId(R.id.navigation_invisible)

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

        bottomNavV_5_2bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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
    }
}