package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_5_1adress.*
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5carrito.*

class activity_5_2payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5_2payment)

        ObjectAnimator.ofInt(progressBar_5_2, "progress", 66)
            .setDuration(1000)
            .start()

        imgv_5_2atras.setOnClickListener(){
            val i = Intent(this@activity_5_2payment, activity_5_1adress::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
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
            this.overridePendingTransition(0, 0)
        }
    }
}