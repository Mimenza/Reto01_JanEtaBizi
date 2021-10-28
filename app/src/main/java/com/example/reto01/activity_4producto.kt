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
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import java.io.File
import java.util.ArrayList

class activity_4producto : AppCompatActivity() {
    var product: Producto = Producto()
    lateinit private var user: User
    lateinit var databaseHelper: DatabaseHelper
    private val activity = this

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        setContentView(R.layout.activity_4producto)
        loadProductData()
        initObjects()
        //-----------------------------------------------------------------------------------------

        //Recoger datos del usuario loggeado
        val prefs: SharedPreferences =
            this@activity_4producto.getSharedPreferences("loggedUser", 0)
        val correo = prefs.getString("correo", null)
        //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
        user = databaseHelper.getUser(correo.toString())!!

        //Comprobamos si el usuario tiene ese producto likeado
        var checkLike = databaseHelper.checkLike(product.id_product, user.id)

        //Cambiamos el color del like
        if (checkLike){
            imgv_4favorite.setImageResource(R.drawable.ic_favoritered)
        }
        //-----------------------------------------------------------------------------------------

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

        loadCarritoNumber()


        btn_4addtocart.setOnClickListener(){
            val prefs: SharedPreferences = getSharedPreferences("carritoProductos", 0)
            val editor: SharedPreferences.Editor = prefs.edit()
            val gson = Gson()

            val itemJson = gson.toJson(product)
            var productos: ArrayList<Producto> = arrayListOf()

            var length = prefs.getString("length", null)

            if(length != null){
                for(i in 0..length.toInt()-1){
                    val productJson = prefs.getString(i.toString(),null)
                    val product: Producto = gson.fromJson(productJson, Producto::class.java)
                    productos.add(product)
                }
            }

            if(!productos.isEmpty()){
                var found = false
                for (i in productos){
                    if (i.id_product == product.id_product){
                        found = true
                        break
                    }
                }

                if(!found){
                    length = (length!!.toInt() + 1).toString()
                    editor.putString("length", length)
                    editor.putString((length!!.toInt() - 1).toString(), itemJson.toString())
                }
            }else{
                if (length != null) {
                    length = (length.toInt() + 1).toString()
                    editor.putString("length", length)
                } else {
                    length = "1"
                    editor.putString("length", length)
                }
                editor.putString((length!!.toInt() - 1).toString(), itemJson.toString())
            }

            editor.commit()

            btn_4addtocart.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_4addtocart.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false

            loadCarritoNumber()
        }

        btn_7_2_2update.setOnTouchListener { v, event ->
            btn_7_2_2update.setBackgroundResource(R.drawable.my_button_border_clickgreen)

            Handler().postDelayed({
                btn_7_2_2update.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
        }

        imgv_7_2_2atras.setOnClickListener() {
            val i = Intent(this, activity_3principal::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        imgv_4favorite.setOnClickListener() {
            if(databaseHelper.checkLike(product.id_product, user.id)){
                //Borramos el producto de like
                databaseHelper.deletelike(product.id_product)
                //Cambiamos la imagen
                imgv_4favorite.setImageResource(R.drawable.ic_favorite)

            } else{
                //Añadimos el producto a like
                databaseHelper.generatelike(product.id_product, user. id)
                //Cambiamos la imagen
                imgv_4favorite.setImageResource(R.drawable.ic_favoritered)
            }
        }

        //Producto  descripción scroll
        txtv_4_descripcionproducto.movementMethod = ScrollingMovementMethod()
    }

    fun loadProductData() {
        val prefs: SharedPreferences = this.getSharedPreferences("product", 0)
        val gsonFile = Gson()

        product = gsonFile.fromJson(prefs.getString("product", null), Producto::class.java)

        txtv_4nombreProducto.setText(resources.getString(product.name_product!!.toInt()))
        txtv_4_descripcionproducto.setText(resources.getString(product.description!!.toInt()))

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

    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

    }

    fun loadCarritoNumber(){
        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_4bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }
}



