package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_2registrar.*

class activity_2registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_2registrar)

        window.decorView.apply {
            //esconde el nav inferior
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        }

        txtv_2Login.setOnClickListener(){

            val i = Intent(this@activity_2registrar, activity_1login::class.java)
            startActivity(i)
        }

        btn_2Register.setOnTouchListener{  v, event ->
            btn_2Register.setBackgroundResource(R.drawable.my_button_border_click);
            Handler().postDelayed({
                btn_2Register.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }
    }
}