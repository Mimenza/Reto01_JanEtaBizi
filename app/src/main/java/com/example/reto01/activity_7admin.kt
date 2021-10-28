package com.example.reto01


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_7admin.*
import java.io.File

class activity_7admin : AppCompatActivity() {
    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_7admin)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        bottomNavV_7bottomMenu.setSelectedItemId(R.id.navigation_perfil)

        loadCarritoNumber()

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
            this.overridePendingTransition(0,0)
        }
        btn_7productos.setOnClickListener() {
            val i = Intent(this, activity_7_2productos::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
        }
        btn_7pedidos.setOnClickListener() {
            val i = Intent(this, activity_7_3_3pedidos::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
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
    fun loadCarritoNumber(){
        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_7bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }
}
