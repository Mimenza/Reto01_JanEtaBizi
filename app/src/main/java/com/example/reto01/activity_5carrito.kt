package com.example.reto01

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto01.Adapter.MyCardsCartAdapter
import com.example.reto01.Model.Carrito_item
import com.example.reto01.Model.Producto
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5carrito.*
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.viewholder_cart.*
import java.io.File
import java.util.*

class activity_5carrito : AppCompatActivity() {
    var total:Double?=0.00
    var carritoSize:Int = 0
    var productos: ArrayList<Producto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5carrito)
        calcularTotal()

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

        btn_5borrarcarrito.setOnTouchListener { v, event ->
            btn_5borrarcarrito.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5borrarcarrito.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }

        btn_5carrito.setOnClickListener() {
            val i = Intent(this@activity_5carrito, activity_5_1address::class.java)
            i.putExtra("total",total)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        btn_5borrarcarrito.setOnClickListener(){
        //Borramos los shared preferences del carrito
            val settings: SharedPreferences = this.getSharedPreferences("carrito", MODE_PRIVATE)
            settings.edit().clear().commit()
            val settings1: SharedPreferences = this.getSharedPreferences("carritoProductos", MODE_PRIVATE)
            settings1.edit().clear().commit()

            //Recargamos la activity
            finish()
            startActivity(getIntent())
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

        val prefs: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
        val editor : SharedPreferences.Editor = prefs.edit()
        val gson = Gson()

        val f = File("/data/data/com.example.reto01/shared_prefs/carrito.xml")
        if (f.exists()){
            f.delete()
        }

        var length = prefs.getString("length", null)

        if(length !=  null){
            for(i in 0..length.toInt()-1){
                val productJson = prefs.getString(i.toString(),null)
                val product: Producto = gson.fromJson(productJson, Producto::class.java)
                productos.add(product)
            }
        }

       /* carritoSize = productos.size*/

        //Adaptador RecyclerView Carrito de la compra
        val adapter = MyCardsCartAdapter(productos, this)
        reciclerView_carrito.layoutManager = LinearLayoutManager(this)
        reciclerView_carrito.adapter = adapter
    }

    fun calcularTotal() {
        var total:Double?=0.00

        //Recoger datos de Shared Preferences
        val prefs: SharedPreferences = this.getSharedPreferences("carrito", 0)


        for(x in productos){
            val carrito = prefs.getString("item" + x.id_product.toString(),null)

            if(carrito != null){
                //Parsear datos a objeto carrito_item
                val gsonFile = Gson()
                val carritoJson: Carrito_item = gsonFile.fromJson(carrito, Carrito_item::class.java)

                val cantidad:Double? = carritoJson.cantidad!!.toDouble()
                val precio:Double? = carritoJson.precio!!.toDouble()
                val totalItem:Double? = cantidad!! * precio!!
                total = total!! + totalItem!!
            }
        }
        var euro= "€"
        txtv_5preciototal.text = total.toString()+euro

        //Poner nombre al fichero
        val preferences = this.getSharedPreferences("totalCarrito", 0)
        val editor : SharedPreferences.Editor= preferences.edit()

        //Subir datos
        editor.putString("total", total?.toString())
        editor.putString("size", carritoSize.toString())
        editor.commit()

        Handler().postDelayed({
            calcularTotal()
        }, 1000)

    }


}
