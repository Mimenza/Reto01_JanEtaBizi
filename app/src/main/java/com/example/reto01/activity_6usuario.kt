package com.example.reto01

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_6usuario.*


class activity_6usuario : AppCompatActivity() {

    lateinit var bottomsheet: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_6usuario)

        bottomsheet = findViewById(R.id.imgv6_bottomsheet)

        bottomsheet.setOnClickListener {

            showDialog()
        }




        bottomNavV_6bottomMenu.setSelectedItemId(R.id.navigation_perfil)

        imgv6_admin.setOnClickListener(){
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }


        fun navegacion_principal() {
            val navegacion_compra = Intent(this, activity_3principal::class.java)
            startActivity(navegacion_compra)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_likes() {
            val navegacion_likes = Intent(this, activity_8likes::class.java)
            startActivity(navegacion_likes)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_carrito() {
            val navegacion_perfil = Intent(this, activity_5carrito::class.java)
            startActivity(navegacion_perfil)
            this.overridePendingTransition(0, 0)

        }

        fun navegacion_perfil() {
            val navegacion_usuario = Intent(this, activity_6usuario::class.java)
            startActivity(navegacion_usuario)
            this.overridePendingTransition(0, 0)

        }

        bottomNavV_6bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_principal -> {
                    navegacion_principal()
                    true
                }
                R.id.navigation_likes -> {
                    navegacion_likes()
                    true
                }
                R.id.navigation_carrito -> {
                    navegacion_carrito()
                    true
                }
                R.id.navigation_perfil -> {
                    navegacion_perfil()
                    true
                }
                else -> false
            }
        }

        btn_6save.setOnTouchListener{  v, event ->
            btn_6save.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }



    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet)
        val languageLayout = dialog.findViewById<LinearLayout>(R.id.layoutIdiomas)
        val fondoLayout = dialog.findViewById<LinearLayout>(R.id.layoutFondo)
        val logoutLayout = dialog.findViewById<LinearLayout>(R.id.logoutLayout)

        languageLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Idioma is Clicked", Toast.LENGTH_SHORT).show()
        }
        fondoLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Tema is Clicked", Toast.LENGTH_SHORT).show()
        }
        logoutLayout.setOnClickListener {
            val i = Intent(this, activity_1login::class.java)
            startActivity(i)
        }


        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


    }

}