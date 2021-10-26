package com.example.reto01

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.example.reto01.Model.Producto
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_4producto.*

class activity_4producto : AppCompatActivity() {
    var product: Producto = Producto()
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        setContentView(R.layout.activity_4producto)
        loadProductData()

        bottomNavV_4bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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


        btn_7_2_2eliminar.setOnTouchListener { v, event ->
            btn_7_2_2eliminar.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_2_2eliminar.setBackgroundResource(R.drawable.my_button_border)
            }, 100)

            val prefs: SharedPreferences = getSharedPreferences("carritoProductos", 0)
            val editor : SharedPreferences.Editor = prefs.edit()
            val gson = Gson()

            val itemJson = gson.toJson(product)

            //Subir datos
            editor.putString(product.id_product.toString(), itemJson.toString())
            editor.commit()

            false
        }

        btn_7_2_2actualizar.setOnTouchListener { v, event ->

            btn_7_2_2actualizar.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_2_2actualizar.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false

        }

        imgv_7_2_2atras.setOnClickListener() {
            val i = Intent(this, activity_3principal::class.java)
            startActivity(i)
        }

        imgv_4favorite.setOnClickListener() {
            //Producto Favorito snackbar
            Snackbar.make(imgv_4favorite, "Producto añadido", Snackbar.LENGTH_SHORT)
                .show()
        }

        //Producto  descripción scroll
        txtv_7_1_1descripcionproducto.movementMethod = ScrollingMovementMethod()
    }

    fun loadProductData() {
        val prefs: SharedPreferences = this.getSharedPreferences("product", 0)
        val gsonFile = Gson()

        product = gsonFile.fromJson(prefs.getString("product", null), Producto::class.java)

        txtv_7_2_2nombreProducto.setText(product.name_product)
        imgv_7_2_2producto.setImageResource(product.img)
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



