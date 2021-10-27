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
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_2_2producto)
        getSupportActionBar()?.hide()


         initObjects()
        imgv_7_2_2atras.setOnClickListener() {
            val i = Intent(this, activity_7_2productos::class.java)
            startActivity(i)
        }

         //Rellenar con los datos del input
        //txtv_7_2_2nombreProducto.setText(intent.getStringExtra("title"))
       // txtinput_7_1_1nombre.setText(intent.getStringExtra("category"))
       // imgv_7_2_2producto.setImageResource(intent.getIntExtra("img"))
        //txtv_7_1_1descripcionproducto.setText(intent.getStringExtra("price"))



        val prefs: SharedPreferences = this.getSharedPreferences("product", 0)
        val gsonFile = Gson()

        product = gsonFile.fromJson(prefs.getString("product", null), Producto::class.java)

        txtv_4nombreProducto.setText(resources.getString(product.name_product!!.toInt()))
        txtv_7_2_2_descripcionproducto.setText(resources.getString(product.name_product!!.toInt()))


        imgv_7_2_2producto.setImageResource(product.img)
        var id_product= product.id_product

        btn_7_2_2delete.setOnClickListener() {
            showDeleteDialog(id_product)
        }
        btn_7_2_2update.setOnClickListener(){
            updateProduct()

        }

    }

    fun updateProduct() {
        product = Producto()

        product.name_product= txtv_7_2_2nombreProducto.text.toString()
        product.description=txtv_7_2_2_descripcionproducto.text.toString()
        product.category=txtv_7_2_2_categoriaproducto.text.toString()
        product.price=txtv_7_2_2_precioproducto.text.toString().toDouble()

        //------------------------------------------------------------

        product.likes= product.likes?.let { intent.getIntExtra("likes", it) }
        databaseHelper.updateProduct(product)

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


