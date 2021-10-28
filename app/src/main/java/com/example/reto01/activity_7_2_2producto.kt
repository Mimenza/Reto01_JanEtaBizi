package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.reto01.Model.Producto
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*
import kotlinx.android.synthetic.main.activity_7_2_2producto.*
import kotlinx.android.synthetic.main.activity_7_2_2producto.btn_7_2_2update
import kotlinx.android.synthetic.main.activity_7_2_2producto.imgv_7_2_2atras
import kotlinx.android.synthetic.main.activity_7_2_2producto.imgv_7_2_2producto


class activity_7_2_2producto : AppCompatActivity() {
    lateinit private var product : Producto
    lateinit private var producto : Producto
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_2_2producto)
        getSupportActionBar()?.hide()


         initObjects()
        imgv_7_2_2atras.setOnClickListener() {
            val i = Intent(this, activity_7_2productos::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
        }

         //Rellenar con los datos del input
        //txtv_7_2_2nombreProducto.setText(intent.getStringExtra("title"))
       // txtinput_7_1_1nombre.setText(intent.getStringExtra("category"))
       // imgv_7_2_2producto.setImageResource(intent.getIntExtra("img"))
        //txtv_7_1_1descripcionproducto.setText(intent.getStringExtra("price"))



        val prefs: SharedPreferences = this.getSharedPreferences("product", 0)
        val gsonFile = Gson()

        product = gsonFile.fromJson(prefs.getString("product", null), Producto::class.java)
        println(product)


        txtv_7_2_2_descripcionproducto.setText(resources.getString(product.description!!.toInt()))
        txtv_7_2_2nombreProducto.setText(resources.getString(product.name_product!!.toInt()))
        txtv_7_2_2_categoriaproducto.setText(product.category)
        txtv_7_2_2_precioproducto.setText(product.price.toString())

        imgv_7_2_2producto.setImageResource(product.img)
        var id_product= product.id_product



        btn_7_2_2delete.setOnTouchListener { v, event ->
            btn_7_2_2delete.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_2_2delete.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }

        btn_7_2_2update.setOnTouchListener { v, event ->
            btn_7_2_2update.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_2_2update.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }
        btn_7_2_2delete.setOnClickListener() {
            showDeleteDialog(id_product)
        }
        btn_7_2_2update.setOnClickListener(){
            updateProduct(id_product)


        }

    }

    fun updateProduct(id_product:Int?) {
        producto = Producto()

        producto.id_product= id_product
        //producto.name_product= txtv_7_2_2nombreProducto.text.toString()
        producto.name_product= product.name_product
        producto.price=txtv_7_2_2_precioproducto.text.toString().toDouble()
        producto.category=txtv_7_2_2_categoriaproducto.text.toString()
        producto.stock = product.stock
        producto.img = product.img
        producto.likes = product.likes
        //producto.description=txtv_7_2_2_descripcionproducto.text.toString()
        producto.description = product.description

        //------------------------------------------------------------

        println("FUN"+producto)
        Toast.makeText(this,"Actualizando el producto...",Toast.LENGTH_LONG).show()

        databaseHelper.updateProduct(producto)


    }
    fun initObjects(){

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)
    }
    //Delete dialog

    fun showDeleteDialog(id_product:Int?) {

        MaterialAlertDialogBuilder(this,
            R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_FullWidthButtons)
            .setMessage(resources.getString(R.string.txt5_eliminarproducto))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->

            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->



                Toast.makeText(this, "Producto eliminado correctamente!", Toast.LENGTH_SHORT).show()
                databaseHelper.deleteProduct(id_product)
                Handler().postDelayed({

                    val i = Intent(this, activity_7_2productos::class.java)
                    startActivity(i)
                }, 1000)

            }
            .show()
    }



}


