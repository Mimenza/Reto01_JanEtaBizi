import com.example.reto01.R
import com.example.reto01.activity_2registrar

Skip to content
Pull requests
Issues
Marketplace
Explore
@julenarma
Mimenza /
Reto01_JanEtaBizi
Private

1
0

0

Code
Issues
Pull requests
Actions
Projects
Security

Insights

Reto01_JanEtaBizi/app/src/main/java/com/example/reto01/activity_1login.kt
Julen Carrito
Latest commit ddd0e15 2 days ago
History
3 contributors
@Mimenza
@olanoalbat
@GalderGG
42 lines (35 sloc) 1.34 KB
package com.example.reto01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_2registrar.*
import java.util.*

class activity_1login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_1login)

        txtv_1Registrar.setOnClickListener(){
            val i = Intent(this@activity_1login, activity_2registrar::class.java)
            startActivity(i)

        }

        <<<<<<< HEAD
        btn_5comprar.setOnTouchListener{ v, event ->
            btn_5comprar.setBackgroundResource(R.drawable.my_button_border_click);
            =======
            btn_1login.setOnClickListener(){
                val i = Intent(this@activity_1login, activity_3principal::class.java)
                startActivity(i)
            }
            btn_1login.setOnTouchListener{  v, event ->
                btn_1login.setBackgroundResource(R.drawable.my_button_border_click);
                >>>>>>> 7d085b2620fda6a3040f8e23676c09efd8739bab
                Handler().postDelayed({
                    btn_5comprar.setBackgroundResource(R.drawable.my_button_border);
                }, 100)

                false

            }
        }
    }

