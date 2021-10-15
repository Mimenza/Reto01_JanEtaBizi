package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*

class activity_5_2payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5_2payment)
        getSupportActionBar()?.hide()

        imgv_5_2atras.setOnClickListener(){
            val i = Intent(this@activity_5_2payment, activity_5_1adress::class.java)
            startActivity(i)
        }

        btn_5_2payment.setOnTouchListener{ v, event ->
            btn_5_2payment.setBackgroundResource(R.drawable.my_button_border_clickgreen);
            Handler().postDelayed({
                btn_5_2payment.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }
        btn_5_2payment.setOnClickListener(){

            val i = Intent(this@activity_5_2payment, activity_5_3gracias::class.java)
            startActivity(i)
        }
    }
}