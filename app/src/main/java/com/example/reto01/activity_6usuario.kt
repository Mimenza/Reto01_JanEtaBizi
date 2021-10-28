package com.example.reto01

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.example.reto01.Model.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.activity_8likes.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.io.File
import java.util.*

class activity_6usuario : AppCompatActivity() {
    lateinit private var user :  User
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this

    lateinit var bottomsheet: ImageView
    //Inputs

    override fun onRestart() {
        super.onRestart()
        bottomNavV_6bottomMenu.setSelectedItemId(R.id.navigation_perfil)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_6usuario)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        var sharedPreferences= getSharedPreferences("data", 0)


        loadCarritoNumber()
        initObjects()

        bottomsheet = findViewById(R.id.imgv6_bottomsheet)

        bottomsheet.setOnClickListener {
            showDialog()
        }

        bottomNavV_6bottomMenu.setSelectedItemId(R.id.navigation_perfil)

        bottomNavV_6bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        imgv6_admin.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
        }

        btn_6reset.setOnTouchListener { v, event ->
            btn_6reset.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_6reset.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }

        btn_6save.setOnTouchListener { v, event ->
            btn_6save.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }

        btn_6save.setOnClickListener(){
            updateUser()
        }
        btn_6reset.setOnClickListener(){
            rellenarCampos()
        }

    }


    fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet)
        val languageLayout = dialog.findViewById<LinearLayout>(R.id.layoutIdiomas)
        val fondoLayout = dialog.findViewById<LinearLayout>(R.id.layoutTema)
        val logoutLayout = dialog.findViewById<LinearLayout>(R.id.logoutLayout)
        val temaLayout = dialog.findViewById<LinearLayout>(R.id.layoutTema)
        val seebuzon= dialog.findViewById<LinearLayout>(R.id.layoutBuzon)
        var deleteaccount= dialog.findViewById<TextView>(R.id.txtv_deleteaccount)

        languageLayout.setOnClickListener {

            chooseLanguageDialog()
        }

        fondoLayout.setOnClickListener {
            val i = Intent(this, activity_6_2tema::class.java)
            startActivity(i)
        }

        logoutLayout.setOnClickListener {
            var sharedPreferences = getSharedPreferences("loggedUser", 0)
            var editor = sharedPreferences.edit()

            editor.putString("correo", "noLoggedUser").apply()

            val i = Intent(this, activity_1login::class.java)
            startActivity(i)
        }

        temaLayout.setOnClickListener{
            chooseThemeDialog()
        }

        seebuzon.setOnClickListener(){
            val i = Intent(this, activity_10buzon::class.java)
            startActivity(i)
        }

        deleteaccount.setOnClickListener{

            showDeleteDialog()

        }


        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    //IDIOMAS DIALOG
    fun chooseLanguageDialog() {
        val english = getString(R.string.ingles)
        val spanish = getString(R.string.español)
        val frances = getString(R.string.frances)
        val euskera = getString(R.string.euskera)

        val languages = arrayOf(spanish, euskera,  frances, english)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.Idiomas))
        val yes=MyPreferences(this).darkMode
        val checkedItem = MyPreferences(this).lang

        builder.setSingleChoiceItems(languages, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    setLocate("es")
                    recreate()

                    MyPreferences(this).lang = 0
                    dialog.dismiss()
                }
                1 -> {
                    setLocate("eu")
                    recreate()

                    MyPreferences(this).lang = 1
                    dialog.dismiss()
                }
                2 -> {
                    setLocate("fr")
                    recreate()

                    MyPreferences(this).lang = 2
                    dialog.dismiss()
                }
                3 -> {

                    setLocate("en")
                    recreate()

                    MyPreferences(this).lang = 3
                    dialog.dismiss()
                }

            }

        }
        val dialog = builder.create()
        dialog.show()
    }

    //for change language
    fun setLocate(lang: String) {
        val locale= Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale =locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        //   val editor = getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
        //editor.putString("my_lang", lang)
        // editor.apply()
    }


    //TEMAS DIALOG
    fun chooseThemeDialog() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.tema))
        val styles = arrayOf("Oscuro","Claro","Por defecto")
        val checkedItem = MyPreferences(this).darkMode


        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                    MyPreferences(this).darkMode = 0

                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    MyPreferences(this).darkMode = 1


                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    delegate.applyDayNight()
                    MyPreferences(this).darkMode = 2

                    dialog.dismiss()
                }
            }

        }
        val dialog = builder.create()
        dialog.show()
    }

    class MyPreferences(context: Context?) {

        companion object {
            private const val DARK_STATUS = "io.github.manuelernesto.DARK_STATUS"
            private const val LANGUAGE ="language"

        }

        private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        var darkMode = preferences.getInt(DARK_STATUS, 0)
            set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()
        var lang = preferences.getInt(LANGUAGE, 0)
            set(value) = preferences.edit().putInt(LANGUAGE, value).apply()
    }

    //Delete dialog

    fun showDeleteDialog(){

        MaterialAlertDialogBuilder(this,
            R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_FullWidthButtons)
            .setMessage(resources.getString(R.string.txt5_eliminar))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->

            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->


                //Recoger datos del usuario loggeado
                val prefs: SharedPreferences = this.getSharedPreferences("loggedUser", 0)
                val correo = prefs.getString("correo",null)
                //Llamar a la función deleteuser pasándole el correo que hemos guardado en SharedPreferences



                Toast.makeText(this, "Cuenta eliminada correctamente!", Toast.LENGTH_SHORT).show()
                databaseHelper.deleteUser(correo)
                Handler().postDelayed({

                   /*Nos redirecciona al activity login poniendo al usuario que no está logeado, después de eliminar
                    la cuenta*/

                    var sharedPreferences = getSharedPreferences("loggedUser", 0)
                    var editor = sharedPreferences.edit()

                    editor.putString("user", "noLoggedUser").apply()

                    val i = Intent(this, activity_1login::class.java)
                    startActivity(i)
                }, 1000)

            }
            .show()
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
            val prefs: SharedPreferences = this@activity_6usuario.getSharedPreferences("loggedUser", 0)
            val correo = prefs.getString("correo",null)
            //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
            return databaseHelper.getUser(correo.toString())!!
        }

        override fun onPostExecute(result: User) {


            super.onPostExecute(result)
            user = result
            rellenarCampos()
            administrador()
        }

    }

      fun administrador() {
        //Si no es admin oculta botón admin

            if(user.admin==0){
                imgv6_admin.isVisible= false
               }else{
                imgv6_admin.isVisible= true
            }
    }


    //Rellenar los campos de dirección con los datos del usuario
    fun rellenarCampos(){
        println(user)

        txt_6nombreuser.setText(user.name)
        txtinput_6apellido.setText(user.surname)
        txtinput_6nombre.setText(user.name)
        txtinput_6correo.setText(user.email)
        txtinput_6contrasena.setText(user.password)
        txtinput_6descripcion.setText(user.description)

    }

    fun updateUser(){

        var usuario =User()
        //Recogemos los datos de los inputs

         var nombre = txtinput_6nombre.text.toString()
         var apellido = txtinput_6apellido.text.toString()
         var correo = txtinput_6correo.text.toString()
         var pass = txtinput_6contrasena.text.toString()
         var description = txtinput_6descripcion.text.toString()

        //Rellenamo el objeto de user con los datos

        usuario.id = user.id
        usuario.name = nombre
        usuario.surname = apellido
        usuario.email = correo
        usuario.password = pass
        usuario.address = user.address
        usuario.city = user.city
        usuario.cp = user.cp
        usuario.description=description
        usuario.admin=user.admin
        usuario.tlf = user.tlf
        usuario.ccv = user.ccv
        usuario.caducidad = user.caducidad
        usuario.num_tarjeta= user.num_tarjeta

        databaseHelper.updateUser(usuario)

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

    fun loadCarritoNumber(){
        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_6bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }
}


