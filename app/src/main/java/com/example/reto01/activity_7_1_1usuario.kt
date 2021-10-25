package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.reto01.Model.User
import com.example.reto01.activity_5_2payment.GetDataFromSQLite
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*


class activity_7_1_1usuario : AppCompatActivity() {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_1_1usuario)
        getSupportActionBar()?.hide()

        initObjects()
        imgv7_01_atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }

        btn_7_01Eliminar.setOnTouchListener { v, event ->
            btn_7_01Eliminar.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_01Eliminar.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }

        btn_7_1_1save.setOnTouchListener { v, event ->
            btn_7_1_1save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_1_1save.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }

        //Rellenar con los datos del intent
        txtinput_7_1_1apellido.setText(intent.getStringExtra("surname"))
        txtinput_7_1_1ciudad.setText(intent.getStringExtra("ciudad"))
        txtinput_7_1_1contrasena.setText(intent.getStringExtra("pass"))
        txtinput_7_1_1correo.setText(intent.getStringExtra("correo"))
        txtinput_7_1_1cp.setText(intent.getStringExtra("cp"))
        txtinput_7_1_1descripcion.setText(intent.getStringExtra("desc"))
        txtinput_7_1_1nombre.setText(intent.getStringExtra("name"))
        txtinput_7_1_1tarjeta.setText(intent.getStringExtra("tarjeta"))
        txtinput_7_1_1tlfn.setText(intent.getStringExtra("tlfn"))
        txtinput_7_1_1direccion.setText(intent.getStringExtra("direccion"))


        btn_7_1_1save.setOnClickListener(){
            //recogemos los valores de los input
            updateUser()        }

    }

    private fun updateUser() {
       /* user = User()

        user.surname= txtinput_7_1_1apellido.text.toString()
        user.city=txtinput_7_1_1ciudad.text.toString()
        user.password = txtinput_7_1_1contrasena.text.toString()
        user.email = txtinput_7_1_1correo.text.toString()
        user.cp = txtinput_7_1_1cp.text.toString()
        user.description = txtinput_7_1_1descripcion.text.toString()
        user.name =txtinput_7_1_1nombre.text.toString()
        user.num_tarjeta = txtinput_7_1_1tarjeta.text.toString()
        user.tlf =  txtinput_7_1_1tlfn.toString()
        user.address =txtinput_7_1_1direccion.text.toString()
        //------------------------------------------------------------

        user.id= intent.getIntExtra("id")
        user.ccv= intent.getIntExtra("ccv")
        user.caducidad= intent.getStringExtra("caducidad")
        user.admin= intent.getIntExtra("admin")

        databaseHelper.updateUser(user)*/

    }

    private fun initObjects() {

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

    }




}
