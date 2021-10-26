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
import kotlinx.android.synthetic.main.activity_4producto.*

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

        btn_4addtocart.setOnTouchListener { v, event ->
            btn_4addtocart.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_4addtocart.setBackgroundResource(R.drawable.my_button_border)
            }, 100)


            val prefs: SharedPreferences = getSharedPreferences("carritoProductos", 0)
            val editor: SharedPreferences.Editor = prefs.edit()
            val gson = Gson()

            val itemJson = gson.toJson(product)

            var length = prefs.getString("length", null)
            if (length != null) {
                length = (length.toInt() + 1).toString()
                editor.putString("length", length)
            } else {
                length = "1"
                editor.putString("length", length)
            }

            editor.putString((length.toInt() - 1).toString(), itemJson.toString())
            editor.commit()

            btn_4addtocart.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_4addtocart.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }

        btn_4buy.setOnTouchListener { v, event ->
            btn_4buy.setBackgroundResource(R.drawable.my_button_border_clickgreen)

            Handler().postDelayed({
                btn_4buy.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
        }

        imgv_7_2_2atras.setOnClickListener() {
            val i = Intent(this, activity_3principal::class.java)
            startActivity(i)
        }

        imgv_4favorite.setOnClickListener() {

            //Recoger datos del usuario loggeado
            val prefs: SharedPreferences =
                this@activity_4producto.getSharedPreferences("loggedUser", 0)
            val correo = prefs.getString("correo", null)
            //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
            user = databaseHelper.getUser(correo.toString())!!


            if(databaseHelper.checkLike(product.id_product, user.id)){
                //Este usuario  le ha dado like al producto

                    println("BORRANDO")
                //Borramos el producto de like
                databaseHelper.deletelike(product.id_product)

            } else{
                //Este usuario no le ha dado like al producto

                println("GUARDANDO")
                //Añadimos el producto a like
                databaseHelper.generatelike(product.id_product, user. id)

            }




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

    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

    }

    /*fun checklikefun(){
        //Checkeamos si el producto tiene likes o no.

        if(databaseHelper.checkLike(product.id_product, user.id)){
            //Este usuario  le ha dado like al producto
            checklike = true

        } else{
            //Este usuario no le ha dado like al producto
            checklike = false

        }

    }*/
}



