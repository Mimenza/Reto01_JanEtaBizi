package com.example.reto01

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_10buzon.*

class activity_10buzon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_10buzon)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        imgv_10atras.setOnClickListener(){

            val i = Intent(this, activity_6usuario::class.java)
            startActivity(i)
        }

        btn_9enviar.setOnClickListener(){

            var texto = txtv_9dudasinput.text

            Toast.makeText(this, "Enviando....", Toast.LENGTH_LONG).show()

            txtv_9dudasinput.setText("")
            Handler().postDelayed({
                val i = Intent(this, activity_6usuario::class.java)
                startActivity(i)
            }, 4000)
        }

        imgBtn_9red1.setOnClickListener(){
            val url = "https://www.instagram.com/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        imgBtn_9red2.setOnClickListener(){
            val url = "https://twitter.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        imgBtn_9red3.setOnClickListener(){
            val url = "https://www.facebook.com/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}