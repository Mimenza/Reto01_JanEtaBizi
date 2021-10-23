package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto01.Adapter.MyCardsCartAdapter
import com.example.reto01.Model.Carrito_item
import com.example.reto01.Model.Producto
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5carrito.*
import kotlinx.android.synthetic.main.activity_5carrito.imgv_5atras
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.viewholder_cart.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView
import java.io.File

class activity_5carrito : AppCompatActivity() {
    var total:Double?=0.00

    var carritoSize:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5carrito)

        //Llamar funcion para saber el total al cargar la pg
        Handler().postDelayed({
            calcularTotal()
        }, 2)
        //Llamar funcion para cargar los datos
        loadProductos()

        bottomNavV_5bottomMenu.setSelectedItemId(R.id.navigation_carrito)

        bottomNavV_5bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        imgv_5atras.setOnClickListener() {
            val i = Intent(this@activity_5carrito, activity_3principal::class.java)
            startActivity(i)
        }

        btn_5carrito.setOnTouchListener { v, event ->
            btn_5carrito.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5carrito.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }

        btn_5carrito.setOnClickListener() {
            val i = Intent(this@activity_5carrito, activity_5_1address::class.java)
            i.putExtra("total",total)
            startActivity(i)
            this.overridePendingTransition(0, 0)
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

    fun loadProductos(){
        //Crear array list de los productos de carrito
        val productos: ArrayList<Producto>

        var producto01 = Producto(0, "Pasteles", 10.0, "postres", 10, R.drawable.dessert)
        var producto02 = Producto(1, "Omega", 12.0, "suplemento", 10, R.drawable.aceite3)
        var producto03 = Producto(2, "Fresas", 17.0, "fruta", 10, R.drawable.fresa)
        var producto04 = Producto(3, "Arandano", 1.0, "fruta", 10, R.drawable.blueberries)
        var producto05 = Producto(4, "Pasteles", 10.0, "postres", 10, R.drawable.dessert)
        var producto06 = Producto(5, "Omega", 12.0, "suplemento", 10, R.drawable.aceite3)
        var producto07 = Producto(6, "Fresas", 17.0, "fruta", 10, R.drawable.fresa)
        var producto08 = Producto(7, "Arandano", 1.0, "fruta", 10, R.drawable.blueberries)

        //rellenar el array con los productos
        productos = arrayListOf(producto01, producto02, producto03, producto04, producto05, producto06, producto07, producto08)

        carritoSize = productos.size
        //Adaptador RecyclerView Carrito de la compra
        val adapter = MyCardsCartAdapter(productos, this)
        reciclerView_carrito.layoutManager = LinearLayoutManager(this)
        reciclerView_carrito.adapter = adapter
    }

    fun calcularTotal() {



        //Recoger datos de Shared Preferences
        val prefs: SharedPreferences = this.getSharedPreferences("carrito", 0)

        for(x in 0..carritoSize-1){

            val carrito = prefs.getString("item"+ x,null)

            println(carrito)
            //Parsear datos a objeto carrito_item
            val gsonFile = Gson()
            val carritoJson: Carrito_item = gsonFile.fromJson(carrito, Carrito_item::class.java)
            val cantidad:Double? = carritoJson.cantidad!!.toDouble()
            val precio:Double? = carritoJson.precio!!.toDouble()
            val totalItem:Double? = cantidad!! * precio!!
            total = total!! + totalItem!!

        }


        txtv_5preciototal.text = total.toString()


        //Poner nombre al fichero
        val preferences = this.getSharedPreferences("totalCarrito", 0)
        val editor : SharedPreferences.Editor= preferences.edit()

        //Subir datos
        editor.putString("total", total?.toString())
        editor.commit()


    }


}
