package com.example.reto01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.reto01.Adapter.MySliderImageAdapter
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import com.google.gson.Gson
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*
import kotlinx.android.synthetic.main.activity_7_2_2producto.*
import kotlinx.android.synthetic.main.activity_7_2_2producto.imgv_7_2_2atras
import kotlinx.android.synthetic.main.activity_7_2_2producto.imgv_7_2_2producto
import kotlinx.android.synthetic.main.activity_7_2_2producto.txtv_7_2_2nombreProducto

class activity_7_2_2producto : AppCompatActivity() {

    lateinit private var product : Producto
    lateinit var databaseHelper:DatabaseHelper
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_2_2producto)
        getSupportActionBar()?.hide()




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

        txtv_7_2_2nombreProducto.setText(product.name_product)
        imgv_7_2_2producto.setImageResource(product.img)


    }


}


