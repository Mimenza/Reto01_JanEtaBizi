package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.reto01.Helpers.InputValidation
import com.example.reto01.Model.User
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5carrito.*
import java.util.*


class activity_5_1address : AppCompatActivity(), View.OnClickListener {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this
    private lateinit var buttonAddress: Button
    private lateinit var inputValidation:InputValidation
    private lateinit var textInputDireccion:EditText
    private lateinit var textInputCiudad:EditText
    private lateinit var textInputCp:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        setContentView(R.layout.activity_5_1adress)

        initViews()
        initObjects()
        initListeners()

        //Damos color al progress bar
        progressBar_5_1.getProgressDrawable().setColorFilter(
            Color.parseColor("#E2C2B9"), android.graphics.PorterDuff.Mode.SRC_IN);

        bottomNavV_5_1bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        imgv_5_1atras.setOnClickListener() {
            val i = Intent(this@activity_5_1address, activity_5carrito::class.java)

            startActivity(i)
            this.overridePendingTransition(0, 0)
        }

        btn_5_1adress.setOnTouchListener { v, event ->
            btn_5_1adress.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_1adress.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }

      /*  btn_5_1adress.setOnClickListener() {
            actualizarDatos()
            val i = Intent(this@activity_5_1address, activity_5_2payment::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }*/

        ObjectAnimator.ofInt(progressBar_5_1, "progress", 33)
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

    private fun initViews(){

        buttonAddress = btn_5_1adress as Button
        textInputDireccion = txt_5_1direccion as EditText
        textInputCiudad = txt_5_1city as EditText
        textInputCp = txt_5_1cp as EditText


    }

    private fun initObjects() {
        user= User()

        inputValidation = InputValidation(activity)

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    private fun initListeners(){
        btn_5_1adress!!.setOnClickListener(this)

    }
    //Si el cliente le da a realizar pedido, creamos una función para llamar a la SQLite
    override fun onClick(v:View?) {
        when (v) {
            buttonAddress -> validarCampos1()
        }
    }

    private fun validarCampos1(){
        //Si el número de tarjeta está vacío, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(
                textInputDireccion,
                getString(R.string.error_message_direccion)
            )
        ){return}
        //Si la caducidad de la tarjeta está vacío, lanzar mensaje de error
        else  if (!inputValidation!!.isInputEditTextFilled(
                textInputCiudad,
                getString(R.string.error_message_ciudad)
            )
        ){return}
        //Si el cvv está vacío, lanzar mensaje de error
        else  if (!inputValidation!!.isInputEditTextFilled(
                textInputCp,
                getString(R.string.error_message_cp)
            )
        ){return}
        else{

            actualizarDatos()
            val i = Intent(this, activity_5_2payment::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }


    }

    inner class GetDataFromSQLite : AsyncTask<Void, Void, User>() {

          //Recoger datos del usuario
        override fun doInBackground(vararg p0: Void?): User? {


            //Recoger datos del usuario loggeado
            val prefs: SharedPreferences = this@activity_5_1address.getSharedPreferences("loggedUser", 0)
            val correo = prefs.getString("correo",null)
              //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
            return databaseHelper.getUser(correo.toString())!!
        }

        override fun onPostExecute(result:User) {


            super.onPostExecute(result)

           user = result
           rellenarCampos()
        }

    }
    //Rellenar los campos de dirección con los datos del usuario
    fun rellenarCampos(){

        txt_5_1nombre.setText(user.name)
        txt_5_1apellido.setText(user.surname)
        txt_5_1direccion.setText(user.address)
        txt_5_1city.setText(user.city)
        txt_5_1cp.setText(user.cp)
        txt_5_1telefono.setText(user.tlf)

    }

    fun actualizarDatos(){

        var usuario =User()
        //Recogemos los datos de los inputs

        var direccion = txt_5_1direccion.text.toString()
        var ciudad = txt_5_1city.text.toString()

        var cp  = txt_5_1cp.text.toString()
        var nombre = txt_5_1nombre.text.toString()
        var apellido = txt_5_1apellido.text.toString()
        var telefono = txt_5_1telefono.text.toString()

        //Rellenamo el objeto de user con los datos
        usuario.id = user.id
        usuario.name = nombre
        usuario.surname = apellido
        usuario.email = user.email
        usuario.password = user.password
        usuario.address = direccion
        usuario.city = ciudad
        usuario.cp = cp
        usuario.description=user.description
        usuario.admin=user.admin
        usuario.tlf = telefono
        usuario.ccv = user.ccv
        usuario.caducidad = user.caducidad
        usuario.num_tarjeta= user.num_tarjeta

        databaseHelper.updateUser(usuario)
    }



}
