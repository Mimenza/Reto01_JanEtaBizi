package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*


class activity_7_1_1usuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_1_1usuario)
        getSupportActionBar()?.hide()



        imgv7_01_atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }

        btn_7_01Eliminar.setOnTouchListener { v, event ->
            btn_7_01Eliminar.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_01Eliminar.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }

        btn_7_01save.setOnTouchListener { v, event ->
            btn_7_01save.setBackgroundResource(R.drawable.my_button_border_clickgreen)
            Handler().postDelayed({
                btn_7_01save.setBackgroundResource(R.drawable.my_button_border)
            }, 100)
            false
        }
    }
}
