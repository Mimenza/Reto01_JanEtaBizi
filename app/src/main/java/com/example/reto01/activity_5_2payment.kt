package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.reto01.Helpers.InputValidation
import com.example.reto01.Model.Carrito_item
import com.example.reto01.Model.Order
import com.example.reto01.Model.Pedido_producto
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_2registrar.*
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class activity_5_2payment : AppCompatActivity(), View.OnClickListener {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this
    private lateinit var buttonPayment: Button
    private lateinit var inputValidation:InputValidation
    private lateinit var textInputNumeroTarjeta:EditText
    private lateinit var textInputCcvTarjeta:EditText
    private lateinit var textInputCaducidadTarjeta:EditText



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5_2payment)
        loadCarritoNumber()
        initViews()
        initObjects()
        initListeners()

        //Damos color al progress bar
        progressBar_5_2.getProgressDrawable().setColorFilter(
            Color.parseColor("#E2C2B9"), android.graphics.PorterDuff.Mode.SRC_IN);

        //Datepicker campo caducidad
        imgv_5_2datepicker.setOnClickListener { showDatePickerDialog() }

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

       /* btn_5_2payment.setOnClickListener() {
            actualizarDatos()

        }*/

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

    fun showDatePickerDialog(){

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        txt_5_2caducidadtarjeta.setText("$day/$month/$year")
    }
    private fun initViews(){

        buttonPayment = btn_5_2payment as Button
        textInputNumeroTarjeta = txt_5_2cardnumber as EditText
        textInputCcvTarjeta = txt_5_2ccv as EditText
        textInputCaducidadTarjeta = txt_5_2caducidadtarjeta as EditText


    }
    private fun initObjects() {
        user= User()

        inputValidation = InputValidation(activity)

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }


    private fun initListeners(){
        btn_5_2payment!!.setOnClickListener(this)

    }
    //Si el cliente le da a realizar pedido, creamos una función para llamar a la SQLite
    override fun onClick(v: View?) {
        when (v) {
            buttonPayment -> validarCampos()
        }
    }

    private fun validarCampos(){
        //Si el número de tarjeta está vacío, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(
                textInputNumeroTarjeta,
                getString(R.string.error_message_numerotarjeta)
            )
        ){return}
        //Si la caducidad de la tarjeta está vacío, lanzar mensaje de error
     else  if (!inputValidation!!.isInputEditTextFilled(
                textInputCaducidadTarjeta,
                getString(R.string.error_message_caducidadtarjeta)
            )
        ){return}
        //Si el cvv está vacío, lanzar mensaje de error
     else  if (!inputValidation!!.isInputEditTextFilled(
                textInputCcvTarjeta,
                getString(R.string.error_message_cvv)
            )
        ){return}
        else{

            actualizarDatos()
            val i = Intent(this, activity_5_3gracias::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }


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

        var euro= "€"
        //Escribimos el dato
        txtv_5_2preciosubtotal.text = total+euro
        txtv_5_2preciototal.text = total+euro

    }

    fun actualizarDatos(){

        var usuario =User()
        //Recogemos los datos de los inputs

        var tarjeta =  txt_5_2cardnumber.text.toString()
        var ccv = txt_5_2ccv.text.toString().toInt()
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
        usuario.ccv = ccv
        usuario.caducidad = caducidad
        usuario.num_tarjeta= tarjeta

        databaseHelper.updateUser(usuario)

        createOrder()
    }

    fun createOrder(){
        //Funcion para crear un ORDER
        var order = Order()

        //Recogemos el precio total de shared preference
        val prefs: SharedPreferences = this.getSharedPreferences("totalCarrito", 0)
        val total= prefs.getString("total",null).toString().toDouble()

        //Conseguimos la hora local
       // order.date = LocalTime.now().toString() //Rellenamos le objeto de order
        order.total = total
        order.address = user.address
        order.id_user = user.id

        var createdOrder =databaseHelper.addOrder(order)

        createPedidoProducto()
    }

    fun createPedidoProducto(){
        // Funcion para crear los PRODUCTOS DEL  ORDER

        //Recogemos el numero de productos que tenemos en el carrito
        val prefs1: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
        val sizeCarrito= prefs1.getString("length",null).toString().toInt()

        //Recogemos el carrito
        val prefs: SharedPreferences = this.getSharedPreferences("carrito", 0)

        //Recoger el ultimo order de DB, para usar su ID
        var lastOrder =databaseHelper.lastOrder()

        var idProducto:Int = 0
        var cantidad:Int=0

        var keys = mutableListOf<String>()
        var prefsAll: MutableMap<String,Carrito_item>? = prefs.all as MutableMap<String, Carrito_item>?


        prefsAll?.forEach {
            keys.add(it.key)
        }
        println(keys)
        var clave : String? = null

        for(x in 0..keys.size-1){
            //Recoger linea de producto
            var carrito = prefs.getString(keys[x],"{}")

            if(carrito!="{}"){

                //Parsear datos a objeto carrito_item
                var gsonFile = Gson()
                var carritoJson: Carrito_item = gsonFile.fromJson(carrito, Carrito_item::class.java)

                cantidad = carritoJson.cantidad!!.toInt()
                idProducto = carritoJson.id!!

                //===================================================================================
                //Rellenamos el pedidoProducto con los datos que tenemos en el sharedPreferences del carrito

                var pedidoProducto =Pedido_producto()

                if (lastOrder != null) {
                    pedidoProducto.id_order = lastOrder.id_order
                }
                pedidoProducto.id_product = idProducto
                pedidoProducto.quantity = cantidad

                databaseHelper.addOrder_product(pedidoProducto)
            }

        }

        //Borramos los shared preferences del carrito
        val f = File("/data/data/com.example.reto01/shared_prefs/carrito.xml")
        f.delete()

        val f2 = File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml")
        f2.delete()

    }
    fun loadCarritoNumber(){
        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_5_2bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }
}


