package com.example.reto01

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_5_2payment.*
import kotlinx.android.synthetic.main.activity_5_2payment.imgv_5_2atras
import kotlinx.android.synthetic.main.activity_5_3gracias.*

class activity_5_3gracias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_5_3gracias)

        ObjectAnimator.ofInt(progressBar_5_3, "progress", 100)
            .setDuration(1000)
            .start()

        imgv_5_3atras.setOnClickListener(){
            val i = Intent(this@activity_5_3gracias, activity_5_2payment::class.java)
            startActivity(i)
            this.overridePendingTransition(0, 0)
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