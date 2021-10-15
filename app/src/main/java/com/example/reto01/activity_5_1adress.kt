package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_4producto.*
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5carrito.*

class activity_5_1adress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_5_1adress)

        imgv_5_1atras.setOnClickListener(){
            val i = Intent(this@activity_5_1adress, activity_5carrito::class.java)
            startActivity(i)
        }

            btn_5_1adress.setOnTouchListener{ v, event ->
                btn_5_1adress.setBackgroundResource(R.drawable.my_button_border_clickgreen);
                Handler().postDelayed({
                    btn_5_1adress.setBackgroundResource(R.drawable.my_button_border);
                }, 100)

                false

            }
            btn_5_1adress.setOnClickListener(){

                val i = Intent(this@activity_5_1adress, activity_5_2payment::class.java)
                startActivity(i)
            }

    }
}