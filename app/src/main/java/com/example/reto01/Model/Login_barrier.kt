package com.example.reto01.Model

import android.content.Context
import android.content.Intent
import com.example.reto01.activity_3principal
import android.app.Activity

class Login_barrier {
    fun checkLoggedUser(context: Context) {
        var sharedPreferences = context.getSharedPreferences("loggedUser", 0)
        var editor = sharedPreferences.edit()

        if (sharedPreferences.contains("correo")) {
            var user = sharedPreferences.getString("correo", "")
            if (user != "noLoggedUser") {
                val intent = Intent(context, activity_3principal::class.java)

                context.startActivity(intent)
                (context as Activity).finish()
            }
        } else {
            editor.putString("correo", "noLoggedUser").apply()
        }
    }
}