package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.reto01.Adapter.MySliderImageAdapter
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*
import kotlinx.android.synthetic.main.activity_7_2_2producto.*

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
        txtv_7_2_2nombreProducto.setText(intent.getStringExtra("title"))
       // txtinput_7_1_1nombre.setText(intent.getStringExtra("category"))
       // imgv_7_2_2producto.setImageResource(intent.getIntExtra("img"))
        txtv_7_1_1descripcionproducto.setText(intent.getStringExtra("price"))




/*        btn_7_01Eliminar.setOnTouchListener { v, event ->

            btn_7_02save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border)
            }, 100)

            false

        }
        btn_7_02save.setOnTouchListener { v, event ->
            btn_7_02save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_6save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            }, 100)

            false

        }*/

        //Producto  descripción scroll
        // txtv_7_02descripcionproducto.movementMethod = ScrollingMovementMethod()
    }


}


