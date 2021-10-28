package com.example.reto01

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reto01.Helpers.InputValidation
import com.example.reto01.Model.Login_barrier
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_2registrar.*
import java.util.*

class activity_1login : AppCompatActivity(), View.OnClickListener {
    private val activity = this@activity_1login

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var appCompatButtonLogin: AppCompatButton
    private lateinit var textViewLinkRegister: AppCompatTextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onBackPressed() {
        val navegacion_login = Intent(this, activity_1login::class.java)
        startActivity(navegacion_login)
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_1login)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        val login_barrier = Login_barrier()
        login_barrier.checkLoggedUser(this)

        // inicializar las vistas
        initViews()

        // inicializar los listeners
        initListeners()

        // inicializar los objetos
        initObjects()

        //------ESTILOS BOTONES--------
        btn_1login.setOnTouchListener { v, event ->
            btn_1login.setBackgroundResource(R.drawable.my_button_border_click);

            Handler().postDelayed({
                btn_1login.setBackgroundResource(R.drawable.my_button_border);
            }, 100)

            false

        }
    }

    private fun initViews() {

        constraintLayout = ctly_1layout as ConstraintLayout
        textInputEditTextPassword = pText_1inputContrasena as TextInputEditText
        appCompatButtonLogin = btn_1login as AppCompatButton
        textViewLinkRegister = txtv_1Registrar as AppCompatTextView
        textInputEditTextEmail = pText_1inputNombre as TextInputEditText
    }

    private fun initListeners() {
        appCompatButtonLogin!!.setOnClickListener(this)
        textViewLinkRegister!!.setOnClickListener(this)
    }

    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity, "reto1", null, 1)
        inputValidation = InputValidation(activity)
    }

    /**
     * This implemented method is to listen the click on view
     * Si hace click en Login, llama a funcion para verificar
     * @param v
     */
    override fun onClick(v: View) {
        when (v) {
            appCompatButtonLogin -> verifyFromSQLite()
            textViewLinkRegister -> {
                // Navigate to RegisterActivity
                val i = Intent(this@activity_1login, activity_2registrar::class.java)
                startActivity(i)
                this.overridePendingTransition(0,0)
            }
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
        //Si el email está vacío, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextEmail!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }

        //Si la contraseña está vacía, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextPassword!!,
                getString(R.string.error_message_password_empty)
            )
        ) {
            return
        }

        //Si el email está bien escrito (Con @...)
        if (!inputValidation!!.isInputEditTextEmail(
                textInputEditTextEmail!!,
                getString(R.string.error_message_email_correct)
            )
        ) {
            return
        }

        //En caso de no estar vacías, llama a la base de datos para comprobar si existe o no el usuario
        //Si es correcto, hace un intent a la página principal
        if (databaseHelper!!.checkUser(
                textInputEditTextEmail!!.text.toString().trim { it <= ' ' },
                textInputEditTextPassword!!.text.toString().trim { it <= ' ' })
        ) {
            loadLoggedUser()
            val intent = Intent(activity, activity_3principal::class.java)

            emptyInputEditText()

            startActivity(intent)
            finish()
        } else {
            // Si no es correcto lanza un snackbar diciendo que los datos son erroneos
            Snackbar.make(
                constraintLayout!!,
                getString(R.string.error_valid_email_password),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }

    fun loadLoggedUser(){
        var sharedPreferences = getSharedPreferences("loggedUser", 0)
        var editor = sharedPreferences.edit()
        editor.putString("correo", pText_1inputNombre.text.toString()).apply()

    }
}



