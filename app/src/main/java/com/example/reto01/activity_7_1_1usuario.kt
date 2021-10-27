package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.reto01.Model.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
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
        imgv7_1_1atras.setOnClickListener() {
            val i = Intent(this, activity_7_1usuarios::class.java)
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



        val prefs: SharedPreferences = this.getSharedPreferences("user", 0)
        val gsonFile = Gson()

        user = gsonFile.fromJson(prefs.getString("user", null), User::class.java)


        var id_user= user.id
        btn_7_01Eliminar.setOnClickListener(){
            deleteUserDialog(id_user)
            println(id_user)
        }


    }

    private fun deleteUserDialog(id_user:Int?){

        MaterialAlertDialogBuilder(this,
            R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_FullWidthButtons)
            .setMessage(resources.getString(R.string.txt5_eliminar))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->

            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->


                Toast.makeText(this, "Cuenta eliminada correctamente!", Toast.LENGTH_SHORT).show()
                databaseHelper.deleteUserById(id_user)
                Handler().postDelayed({

                    val i = Intent(this, activity_7_1usuarios::class.java)
                    startActivity(i)
                }, 1000)

            }
            .show()


    }

    private fun updateUser() {
        user = User()

        user.surname= txtinput_7_1_1apellido.text.toString()
        user.city=txtinput_7_1_1ciudad.text.toString()
        user.password = txtinput_7_1_1contrasena.text.toString()
        user.email = txtinput_7_1_1correo.text.toString()
        user.cp = txtinput_7_1_1cp.text.toString()
        user.description = txtinput_7_1_1descripcion.text.toString()
        user.name =txtinput_7_1_1nombre.text.toString()
        user.num_tarjeta = txtinput_7_1_1tarjeta.text.toString()
        user.tlf =  txtinput_7_1_1tlfn.text.toString()
        user.address =txtinput_7_1_1direccion.text.toString()
        //------------------------------------------------------------

        user.id= intent.getIntExtra("id",0)
        user.ccv= intent.getIntExtra("ccv",0)
        user.caducidad= intent.getStringExtra("caducidad")
        user.admin= intent.getIntExtra("admin",0)

        databaseHelper.updateUser(user)

    }

    private fun initObjects() {

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

    }




}
