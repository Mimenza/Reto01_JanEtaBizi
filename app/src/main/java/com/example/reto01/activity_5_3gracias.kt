package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.reto01.Model.Order
import com.example.reto01.Model.User
import kotlinx.android.synthetic.main.activity_5_3gracias.*
import java.text.SimpleDateFormat
import java.util.*

class activity_5_3gracias : AppCompatActivity() {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5_3gracias)
        initObjects()


        bottomNavV_5_3bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        imgv_5_3atras.setOnClickListener() {
            val i = Intent(this, activity_5_2payment::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        btn_5_3gracias.setOnTouchListener { v, event ->
            btn_5_3gracias.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_3gracias.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }

        btn_5_3gracias.setOnClickListener() {

            createOrder()

            val i = Intent(this, activity_3principal::class.java)
            startActivity(i)
        }

        ObjectAnimator.ofInt(progressBar_5_3, "progress", 100)
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
            val prefs: SharedPreferences = this@activity_5_3gracias.getSharedPreferences("loggedUser", 0)
            val correo = prefs.getString("correo",null)
            //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
            return databaseHelper.getUser(correo.toString())!!
        }

        override fun onPostExecute(result: User) {

            super.onPostExecute(result)
            user = result
            }

    }

    fun createOrder(){
        var order = Order()

        //Recogemos el total de shared preference
        val prefs: SharedPreferences = this.getSharedPreferences("totalCarrito", 0)
        val total= prefs.getString("total",null).toString().toDouble()

        print(total)
        //Conseguimos la hora local
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss").toString()
        val currentDate = sdf.format(Date())

        //Rellenamos le objeto de order
        order.date = currentDate
        order.total = total
        order.address = user.address
        order.id_user = user.id


        var createdOrder =databaseHelper.addOrder(order)

        createPedidoProducto()
    }

    fun createPedidoProducto(){

    }
}
