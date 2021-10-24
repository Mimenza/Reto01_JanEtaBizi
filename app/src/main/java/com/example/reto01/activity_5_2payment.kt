package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.reto01.Model.Carrito_item
import com.example.reto01.Model.Order
import com.example.reto01.Model.Pedido_producto
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import java.text.SimpleDateFormat
import java.util.*

class activity_5_2payment : AppCompatActivity() {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5_2payment)

        initObjects()

        bottomNavV_5_2bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        imgv_5_2atras.setOnClickListener() {
            val i = Intent(this, activity_5_1address::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        btn_5_2payment.setOnTouchListener { v, event ->
            btn_5_2payment.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_2payment.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }

        btn_5_2payment.setOnClickListener() {
            actualizarDatos()
            val i = Intent(this, activity_5_3gracias::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        ObjectAnimator.ofInt(progressBar_5_2, "progress", 66)
            .setDuration(1000)
            .start()
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
        user= User()

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()



    }


    inner class GetDataFromSQLite : AsyncTask<Void, Void, User>() {

        //Recoger datos del usuario
        override fun doInBackground(vararg p0: Void?): User? {


            //Recoger datos del usuario loggeado
            val prefs: SharedPreferences = this@activity_5_2payment.getSharedPreferences("loggedUser", 0)
            val correo = prefs.getString("correo",null)
            //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
            return databaseHelper.getUser(correo.toString())!!
        }

        override fun onPostExecute(result: User) {

            super.onPostExecute(result)
            user = result
            println(user)
            rellenarCampos()
        }

    }

    fun rellenarCampos(){

        txt_5_2cardnumber.setText(user.num_tarjeta)
        //txt_5_2ccv.setText(user.ccv!!)
        txt_5_2caducidadtarjeta.setText(user.caducidad)

        //recogemos el dato del shared
        val prefs: SharedPreferences = this.getSharedPreferences("totalCarrito", 0)
        val total= prefs.getString("total",null)

        //Escribimos el dato
        txtv_5_2preciosubtotal.text = total
        txtv_5_2preciototal.text = total

    }

    fun actualizarDatos(){

        var usuario =User()
        //Recogemos los datos de los inputs

        var tarjeta =  txt_5_2cardnumber.text.toString()
        //var ccv = txt_5_2ccv.text.toString().toInt()
        var caducidad = txt_5_2caducidadtarjeta.text.toString()

        //Rellenamo el objeto de user con los datos

        usuario.id = user.id
        usuario.name = user.name
        usuario.surname = user.surname
        usuario.email = user.email
        usuario.password = user.password
        usuario.address = user.address
        usuario.city = user.city
        usuario.cp = user.cp
        usuario.description=user.description
        usuario.admin=user.admin
        usuario.tlf = user.tlf
        usuario.ccv = user.ccv
        usuario.caducidad = caducidad
        usuario.num_tarjeta= tarjeta

        databaseHelper.updateUser(usuario)

        createOrder()
    }

    fun createOrder(){
        var order = Order()

        //Recogemos el total de shared preference
        val prefs: SharedPreferences = this.getSharedPreferences("totalCarrito", 0)
        val total= prefs.getString("total",null).toString().toDouble()

        val sizeCarrito=prefs.getString("size",null).toString().toInt()

        //Conseguimos la hora local
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss").toString()
        val currentDate = sdf.format(Date())

        //Rellenamos le objeto de order
        order.date = currentDate
        order.total = total
        order.address = user.address
        order.id_user = user.id

        var createdOrder =databaseHelper.addOrder(order)

        println(createdOrder)
        createPedidoProducto(sizeCarrito)
    }

    fun createPedidoProducto(sizeCarrito: Int){

        //Recoger datos de Shared Preferences
        val prefs: SharedPreferences = this.getSharedPreferences("carrito", 0)

        //Recoger el ultimo order de db
        var lastOrder =databaseHelper.lastOrder()


        for(x in 0..sizeCarrito-1){

            val carrito = prefs.getString("item"+ x,null)


            //Parsear datos a objeto carrito_item
            val gsonFile = Gson()

            val carritoJson: Carrito_item = gsonFile.fromJson(carrito, Carrito_item::class.java)

            val cantidad:Int = carritoJson.cantidad!!.toInt()
            val id:Int? = carritoJson.id


            //===================================================================================
            //Rellenamos el pedidoProducto con los datos que tenemos en el sharedPreferences del carrito

            var pedidoProducto =Pedido_producto()

            if (lastOrder != null) {
                pedidoProducto.id_order = lastOrder.id_order
            }
            pedidoProducto.id_product = id
            pedidoProducto.quantity = cantidad

            databaseHelper.addOrder_product(pedidoProducto)
        }

    }
}
