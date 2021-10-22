package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_8likes.*

class activity_8likes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_8likes)

        sv_8scrollView.setVerticalScrollBarEnabled(false)
        loadTable()

        bottomNavV_8bottomMenu.setSelectedItemId(R.id.navigation_likes)

        bottomNavV_8bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

    fun loadTable() {
        var items = 4
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

                tb_8tablaProductos.addView(newRow)

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

                tb_8tablaProductos.addView(newRow)

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

                tb_8tablaProductos.addView(newRow)

                newCol1.requestLayout()

                newCol1.getLayoutParams().height = 262

                newCol1.getLayoutParams().width = 262

                newCol1.setScaleType(ImageView.ScaleType.FIT_XY)

                i++
            }
        }
    }
}
