import com.example.reto01.R
import com.example.reto01.activity_2registrar
import com.example.reto01.activity_3principal

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

        txtv_1Registrar.setOnClickListener() {
            val i = Intent(this@activity_1login, activity_2registrar::class.java)
            startActivity(i)

        }


            btn_1login.setOnTouchListener { v, event ->
                btn_1login.setBackgroundResource(R.drawable.my_button_border_click);

                Handler().postDelayed({
                    btn_1login.setBackgroundResource(R.drawable.my_button_border);
                }, 100)

                false

            }
        }
    }


