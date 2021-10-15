package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5_2payment.imgv_5_2atras
import kotlinx.android.synthetic.main.activity_5_3gracias.*

class activity_5_3gracias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_5_3gracias)


        imgv_5_3atras.setOnClickListener(){
            val i = Intent(this@activity_5_3gracias, activity_5_2payment::class.java)
            startActivity(i)
        }

        btn_5_3gracias.setOnTouchListener{ v, event ->
            btn_5_3gracias.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_3gracias.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }
        btn_5_3gracias.setOnClickListener(){

            val i = Intent(this@activity_5_3gracias, activity_3principal::class.java)
            startActivity(i)
        }
    }
}