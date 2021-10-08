package com.example.reto01

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_3principal.*
import android.widget.TextView
import android.view.Gravity


class activity_3principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_3principal)
        init()

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

        bottomNavV_3bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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




    }

    fun init() {
        val stk = tl_3tabla as TableLayout
        val tbrow0 = TableRow(this)
        val tv0 = TextView(this)
        tv0.text = " Sl.No "
        tv0.setTextColor(Color.WHITE)
        tbrow0.addView(tv0)
        val tv1 = TextView(this)
        tv1.text = " Product "
        tv1.setTextColor(Color.WHITE)
        tbrow0.addView(tv1)
        val tv2 = TextView(this)
        tv2.text = " Unit Price "
        tv2.setTextColor(Color.WHITE)
        tbrow0.addView(tv2)
        val tv3 = TextView(this)
        tv3.text = " Stock Remaining "
        tv3.setTextColor(Color.WHITE)
        tbrow0.addView(tv3)
        stk.addView(tbrow0)
        for (i in 0..24) {
            val tbrow = TableRow(this)
            val t1v = TextView(this)
            t1v.text = "" + i
            t1v.setTextColor(Color.WHITE)
            t1v.gravity = Gravity.CENTER
            tbrow.addView(t1v)
            val t2v = TextView(this)
            t2v.text = "Product $i"
            t2v.setTextColor(Color.WHITE)
            t2v.gravity = Gravity.CENTER
            tbrow.addView(t2v)
            val t3v = TextView(this)
            t3v.text = "Rs.$i"
            t3v.setTextColor(Color.WHITE)
            t3v.gravity = Gravity.CENTER
            tbrow.addView(t3v)
            val t4v = TextView(this)
            t4v.text = "" + i * 15 / 32 * 10
            t4v.setTextColor(Color.WHITE)
            t4v.gravity = Gravity.CENTER
            tbrow.addView(t4v)
            stk.addView(tbrow)
        }
    }



}

