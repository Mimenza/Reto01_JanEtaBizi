package com.example.reto01

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_6_1idiomas.*
import java.util.*

class activity_6_1idiomas : AppCompatActivity(), View.OnClickListener {

    private lateinit  var myLocale: Locale

    private lateinit var btn_6_1euskera:Button
    private lateinit var btn_6_1español:Button
    private lateinit var btn_6_1ingles:Button
    private lateinit var btn_6_1frances:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_6_1idiomas)


        imgv_6_2back.setOnClickListener {
            val i = Intent(this, R.layout.activity_6usuario::class.java)
            startActivity(i)
        }



        //--------------IDIOMAS----------------


        initViews()
        initListeners()
        loadLocale()


    }

    private fun initViews(){

        btn_6_1euskera = findViewById(R.id.btn_6_1euskera) as Button
        btn_6_1español =  findViewById(R.id.btn_6_1español) as Button
        btn_6_1ingles = findViewById(R.id.btn_6_1ingles) as Button
        btn_6_1frances = findViewById(R.id.btn_6_1frances)  as Button
    }
    private fun initListeners() {
        btn_6_1euskera!!.setOnClickListener(this)
        btn_6_1español!!.setOnClickListener(this)
        btn_6_1ingles!!.setOnClickListener(this)
        btn_6_1frances!!.setOnClickListener(this)
    }

    fun loadLocale() {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE)
        val language = prefs.getString(langPref, "")
        changeLang(language!!)
    }
    fun saveLocale(lang: String?) {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(langPref, lang)
        editor.commit()
    }
    fun changeLang(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        saveLocale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

    }



    override fun onClick(v: View?) {
        var lang = "es"
        when (v) {
            btn_6_1euskera -> lang = "eu"  
            btn_6_1español -> lang = "es"
            btn_6_1ingles -> lang = "en"
            btn_6_1frances -> lang = "fr"
            else -> {
            }
        }
        changeLang(lang)
    }




    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (myLocale != null) {
            newConfig.locale = myLocale
            Locale.setDefault(myLocale)
            baseContext.resources.updateConfiguration(
                newConfig,
                baseContext.resources.displayMetrics
            )
        }
    }
}