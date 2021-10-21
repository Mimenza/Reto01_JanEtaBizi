package com.example.reto01

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.activity_1login.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_2registrar.*
import org.w3c.dom.Text
import java.util.*

class activity_6usuario : AppCompatActivity() {
    internal val admin= DatabaseHelper(this, "reto1", null, 1)

    lateinit var bottomsheet: ImageView
    //Inputs

     lateinit  var emailText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_6usuario)

        var sharedPreferences= getSharedPreferences("data", 0)

        val email: String? = sharedPreferences.getString("loggedUser", "")


         txtinput_6correo.setText(email)



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
        }

        btn_6reset.setOnTouchListener { v, event ->
            btn_6reset.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_6reset.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

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
        var deleteaccount= dialog.findViewById<TextView>(R.id.txtv_deleteaccount)

        languageLayout.setOnClickListener {

            chooseLanguageDialog()
        }

        fondoLayout.setOnClickListener {
            val i = Intent(this, activity_6_2tema::class.java)
            startActivity(i)
        }

        logoutLayout.setOnClickListener {
            val i = Intent(this, activity_1login::class.java)
            startActivity(i)
        }

        temaLayout.setOnClickListener{
            chooseThemeDialog()
        }

        deleteaccount.setOnClickListener{
2

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
}


