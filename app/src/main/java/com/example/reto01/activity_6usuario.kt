package com.example.reto01

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
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
        val fondoLayout = dialog.findViewById<LinearLayout>(R.id.layoutFondo)
        val logoutLayout = dialog.findViewById<LinearLayout>(R.id.logoutLayout)

        languageLayout.setOnClickListener {
            val i = Intent(this, activity_6_1idiomas::class.java)
            startActivity(i)
        }

        fondoLayout.setOnClickListener {
            val i = Intent(this, activity_6_2tema::class.java)
            startActivity(i)
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
