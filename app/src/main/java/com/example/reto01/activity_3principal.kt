package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import java.io.File

class activity_3principal : AppCompatActivity() {
    lateinit private var listProducts: MutableList<Producto>
    lateinit var databaseHelper: DatabaseHelper
    private val activity = this

    override fun onBackPressed() {
        navegacion("navigation_principal")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_3principal)
        loadCarritoNumber()
        initObjects()
        sv_3scrollView.setVerticalScrollBarEnabled(false)
        sv_3scrollViewFiltro.setVerticalScrollBarEnabled(false)

        bottomNavV_3bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

        /* var user0 = User()
         user0.id=R.drawable.polvobatido
         var user1 = User()
         user1.id=R.drawable.barrita
         var user2 = User()
         user2.id=R.drawable.cremablanca
         var user3 = User()
         user3.id=R.drawable.glucosport
         var user4 = User()
         user4.id=R.drawable.colnatur

         println("U S U A R I O "+ user0)
         println("U S U A R I O "+ user1)
         println("U S U A R I O "+ user2)
         println("U S U A R I O "+ user3)
         println("U S U A R I O "+ user4)*/

        //COMIDA VEGANA
        tv_3vegan.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("vegan")
        }
        //COMIDA DEPORTE
        tv_3deporte.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("deporte")
        }
        //COMIDA SIN LACTEOS
        tv_3leche.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("lacteo")
        }
        //COMIDA CELIACOS
        tv_3lceliaco.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("celiaco")
        }
        //COMIDA SIN ACEITE DE PALMA
        /*tv_3palm.setOnClickListener(){
            sv_3scrollView.isVisible= true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("palmOilFree")
        }*/
        //COMIDA KM0
        tv_3km0.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("KM0")
        }
        //COMIDA SIN HUEVO
        tv_3huevo.setOnClickListener() {
            sv_3scrollView.isVisible = true
            sv_3scrollViewFiltro.isVisible = false
            loadTable("huevo")
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

            "navigation_producto" -> {
                val navegacion_compra = Intent(this, activity_4producto::class.java)
                startActivity(navegacion_compra)
            }
        }
        this.overridePendingTransition(0, 0)
    }

    fun loadTable(category: String) {
        var filteredItems: ArrayList<Producto> = ArrayList()

        for (i in 0..listProducts.size - 1) {
            if (listProducts[i].category == category) {
                filteredItems.add(listProducts[i])
            }
        }

        var rowsLength = (((filteredItems.size + 3) - 1) / 3) - 1
        var itemsLength = filteredItems.size
        var layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(2, 2, 2, 2)

        var i = 0
        var j = 0
        while (i <= rowsLength) {
            if (itemsLength >= 3) {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(filteredItems[j].img)
                newCol2.setImageResource(filteredItems[j + 1].img)
                newCol3.setImageResource(filteredItems[j + 2].img)

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

                val sharedPreferences = getSharedPreferences("product", 0)
                val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                val gson = Gson()

                val number = j

                newCol1.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                newCol2.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number + 1])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                newCol3.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number + 2])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                i++
                itemsLength = itemsLength - 3
                j = j + 3
            } else if (itemsLength == 2) {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(filteredItems[j].img)
                newCol2.setImageResource(filteredItems[j + 1].img)

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

                val sharedPreferences = getSharedPreferences("product", 0)
                val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                val gson = Gson()

                val number = j

                newCol1.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                newCol2.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number + 1])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                i++
            } else {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(filteredItems[i].img)

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

                val sharedPreferences = getSharedPreferences("product", 0)
                val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                val gson = Gson()

                val number = j

                newCol1.setOnClickListener() {
                    val productJson = gson.toJson(filteredItems[number])
                    sharedPreferencesEditor.putString("product", productJson)
                    sharedPreferencesEditor.commit()

                    navegacion("navigation_producto")
                }

                i++
            }
        }
    }

    private fun initObjects() {
        listProducts = ArrayList()

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Producto>>() {

        override fun doInBackground(vararg p0: Void?): List<Producto> {

            return databaseHelper.getAllProducts()

        }

        override fun onPostExecute(result: List<Producto>?) {
            super.onPostExecute(result)
            listProducts.clear()
            listProducts.addAll(result!!)
            //println(listProducts)
        }
    }

    fun loadCarritoNumber(){

        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_3bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)

            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }

}
