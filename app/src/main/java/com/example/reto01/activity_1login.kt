package com.example.reto01

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_2registrar.*
import java.util.*

class activity_1login : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_1login)

        window.decorView.apply {
            //esconde el nav inferior
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        }


        txtv_1Registrar.setOnClickListener(){

            val i = Intent(this@activity_1login, activity_2registrar::class.java)
            startActivity(i)
        }

        btn_1login.setOnTouchListener{  v, event ->
            btn_1login.setBackgroundResource(R.drawable.my_button_border_click);
            Handler().postDelayed({
                btn_1login.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }
    }
}