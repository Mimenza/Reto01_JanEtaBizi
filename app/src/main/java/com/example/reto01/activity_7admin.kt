package com.example.reto01


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_7admin.*

class activity_7admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_7admin)

        bottomNavV_7bottomMenu.setSelectedItemId(R.id.navigation_perfil)

        bottomNavV_7bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_principal -> {
                    navegacion("navigation_principal")
                    true
                }
                R.id.navigation_likes -> {
                    navegacion("navigation_likes")
                    true
                }
                R.id.navigation_carrito -> {
                    navegacion("navigation_carrito")
                    true
                }
                R.id.navigation_perfil -> {
                    navegacion("navigation_perfil")
                    true
                }
                R.id.navigation_blog -> {
                    navegacion("navigation_blog")
                    true
                }
                else -> false
            }
        }

        btn_7usuarios.setOnClickListener() {
            val i = Intent(this, activity_7_1usuarios::class.java)
            startActivity(i)
        }
        btn_7productos.setOnClickListener() {
            val i = Intent(this, activity_7_2productos::class.java)
            startActivity(i)
        }
        btn_7pedidos.setOnClickListener() {
            val i = Intent(this, activity_7_3_3pedidos::class.java)
            startActivity(i)
        }
    }

    fun navegacion(activity: String) {
        when (activity) {
            "navigation_principal" -> {
                val navegacion_compra = Intent(this, activity_3principal::class.java)
                startActivity(navegacion_compra)
            }

            "navigation_likes" -> {
                val navegacion_compra = Intent(this, activity_8likes::class.java)
                startActivity(navegacion_compra)
            }

            "navigation_carrito" -> {
                val navegacion_compra = Intent(this, activity_5carrito::class.java)
                startActivity(navegacion_compra)
            }

            "navigation_perfil" -> {
                val navegacion_compra = Intent(this, activity_6usuario::class.java)
                startActivity(navegacion_compra)
            }

            "navigation_blog" -> {
                val navegacion_compra = Intent(this, activity_9blog::class.java)
                startActivity(navegacion_compra)
            }
        }
        this.overridePendingTransition(0, 0)
    }
}
