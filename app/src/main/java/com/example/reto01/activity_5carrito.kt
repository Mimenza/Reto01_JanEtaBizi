package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto01.Adapter.MyCardsCartAdapter
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5carrito.*
import kotlinx.android.synthetic.main.activity_5carrito.imgv_5atras
import kotlinx.android.synthetic.main.activity_6usuario.*
import kotlinx.android.synthetic.main.viewholder_cart.*

class activity_5carrito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5carrito)

        bottomNavV_5bottomMenu.setSelectedItemId(R.id.navigation_carrito)

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

        bottomNavV_5bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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



        imgv_5atras.setOnClickListener(){
            val i = Intent(this@activity_5carrito, activity_3principal::class.java)
            startActivity(i)
        }

        btn_5carrito.setOnTouchListener{ v, event ->
            btn_5carrito.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5carrito.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }

        btn_5carrito.setOnClickListener(){
            val i = Intent(this@activity_5carrito, activity_5_1adress::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
        }
      //Adaptador RecyclerView Carrito de la compra
        val adapter=MyCardsCartAdapter()
        reciclerView_5.layoutManager= LinearLayoutManager(this)

        reciclerView_5.adapter=adapter



        //View Holder carrito ids



    }
}