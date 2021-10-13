package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_3principal.*


class activity_3principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_3principal)
        sv_3scrollView.setVerticalScrollBarEnabled(false)
        loadTable()

        cg_3chipgroup.isVisible=false

        var showFilter = true

        imgv_3filtro.setOnClickListener(){
            if (showFilter){
                cg_3chipgroup.isVisible=true
                showFilter=false
            }else{
                cg_3chipgroup.isVisible=false
                showFilter=true
            }
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

        tb_3tablaProductos.setOnClickListener(){
            val navegacion_perfil = Intent(this, activity_4producto::class.java)
            startActivity(navegacion_perfil)
            this.overridePendingTransition(0, 0)
        }
    }

    fun loadTable() {
        var items = 26
        var itemsLength = items
        var rowsLength = (((items + 3) - 1) / 3) - 1
        var layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        var i = 0
        while (i <= rowsLength) {
            if (itemsLength >= 3) {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(R.drawable.prueba)
                newCol2.setImageResource(R.drawable.prueba)
                newCol3.setImageResource(R.drawable.prueba)

                layoutParams.setMargins(2, 2, 2, 2)

                newRow.addView(newCol1, layoutParams)
                newRow.addView(newCol2, layoutParams)
                newRow.addView(newCol3, layoutParams)

                tb_3tablaProductos.addView(newRow)

                newCol1.requestLayout()
                newCol2.requestLayout()
                newCol3.requestLayout()

                newCol1.getLayoutParams().height = 262
                newCol2.getLayoutParams().height = 262
                newCol3.getLayoutParams().height = 262

                newCol1.getLayoutParams().width = 262
                newCol2.getLayoutParams().width = 262
                newCol3.getLayoutParams().width = 262

                newCol1.setScaleType(ImageView.ScaleType.FIT_XY)
                newCol2.setScaleType(ImageView.ScaleType.FIT_XY)
                newCol3.setScaleType(ImageView.ScaleType.FIT_XY)

                i++
                itemsLength = itemsLength - 3
            } else if (itemsLength == 2) {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)

                newCol1.setImageResource(R.drawable.prueba)
                newCol2.setImageResource(R.drawable.prueba)

                layoutParams.setMargins(2, 2, 2, 2)

                newRow.addView(newCol1, layoutParams)
                newRow.addView(newCol2, layoutParams)

                tb_3tablaProductos.addView(newRow)

                newCol1.requestLayout()
                newCol2.requestLayout()

                newCol1.getLayoutParams().height = 262
                newCol2.getLayoutParams().height = 262

                newCol1.getLayoutParams().width = 262
                newCol2.getLayoutParams().width = 262

                newCol1.setScaleType(ImageView.ScaleType.FIT_XY)
                newCol2.setScaleType(ImageView.ScaleType.FIT_XY)

                i++
            } else {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)

                newCol1.setImageResource(R.drawable.prueba)

                layoutParams.setMargins(2, 2, 2, 2)

                newRow.addView(newCol1, layoutParams)

                tb_3tablaProductos.addView(newRow)

                newCol1.requestLayout()

                newCol1.getLayoutParams().height = 262

                newCol1.getLayoutParams().width = 262

                newCol1.setScaleType(ImageView.ScaleType.FIT_XY)

                i++
            }
        }
    }
}

