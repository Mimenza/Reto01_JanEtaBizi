package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import com.example.reto01.Model.Likes
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import kotlinx.android.synthetic.main.activity_3principal.*
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_8likes.*
import java.io.File
import java.util.ArrayList
import kotlin.math.abs

class activity_8likes : AppCompatActivity(),GestureDetector.OnGestureListener {
    lateinit private var user: User
    lateinit var databaseHelper: DatabaseHelper
    private val activity = this

    lateinit var  gestureDetector: GestureDetector
    var x2:Float= 0.0f
    var x1:Float= 0.0f

    companion object{
        const val  MIN_DISTANCE = 150
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_8likes)
        gestureDetector = GestureDetector(this, this)
        initObjects()
        loadCarritoNumber()

        sv_8scrollView.setVerticalScrollBarEnabled(false)

        //Recoger datos del usuario loggeado
        val prefs: SharedPreferences =
            this@activity_8likes.getSharedPreferences("loggedUser", 0)
        val correo = prefs.getString("correo", null)
        //Llamar a la función getUser pasándole el correo que hemos guardado en SharedPreferences
        user = databaseHelper.getUser(correo.toString())!!

        loadTable(user)

        bottomNavV_9_1bottomMenu.setSelectedItemId(R.id.navigation_likes)

        bottomNavV_9_1bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
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

    fun loadTable(user: User)  {
        var listProducts = ArrayList<Producto>()
        //Recomes la lista de likes que tiene ese user
        var listaLikes:MutableList<Likes> =databaseHelper.getUserLikes(user.id)

        //Recorremos el array y buscarmos el producto
        for(i in 0..listaLikes.size-1){

            //Guardamos los datos del producto y lo pusheamos
            var producto = listaLikes[i].id_producto?.let { databaseHelper.getProduct(it) }

            listProducts.add(producto!!)
        }

        println(listProducts)

        var rowsLength = (((listProducts.size + 3) - 1) / 3) - 1
        var itemsLength = listProducts.size
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

                newCol1.setImageResource(listProducts[j].img)
                newCol2.setImageResource(listProducts[j+1].img)
                newCol3.setImageResource(listProducts[j+2].img)

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
                j = j + 3
            } else if (itemsLength == 2) {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(listProducts[j].img)
                newCol2.setImageResource(listProducts[j+1].img)

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
            } else {
                var newRow = TableRow(this)
                var newCol1 = ImageView(this)
                var newCol2 = ImageView(this)
                var newCol3 = ImageView(this)

                newCol1.setImageResource(listProducts[j].img)

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
            }
        }
    }
    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

    }
    fun loadCarritoNumber(){
        if ( File("/data/data/com.example.reto01/shared_prefs/carritoProductos.xml").exists()){
            var badge = bottomNavV_9_1bottomMenu.getOrCreateBadge(R.id.navigation_carrito)
            val prefss: SharedPreferences = this.getSharedPreferences("carritoProductos", 0)
            // An icon only badge will be displayed unless a number is set:
            badge.number = prefss.getString("length",null).toString().toInt()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)

        when (event?.action){
            //cuando empezamos a swipear
            0->{

                x1 = event.x

            }
            //cuando terminamos de swipear
            1->{

                x2 = event.x

                //Compara la distacia que hemos hecho
                val valueX:Float = x2-x1

                println(valueX)

                if(abs(valueX) > MIN_DISTANCE){

                    if(x2>x1){
                        println("DERECHA")
                        Toast.makeText(this, "Right swipe", Toast.LENGTH_LONG).show()
                    }
                    else{
                        println("IZQUIERDA")
                        Toast.makeText(this, "Left swipe", Toast.LENGTH_LONG).show()
                    }
                }


            }


        }

        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        // TODO("Not yet implemented")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        // TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // TODO("Not yet implemented")
        return false
    }}
