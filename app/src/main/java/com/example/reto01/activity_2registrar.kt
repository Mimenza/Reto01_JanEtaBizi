package com.example.reto01


import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reto01.Helpers.InputValidation
import com.example.reto01.Model.User
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_2registrar.*

class activity_2registrar : AppCompatActivity(), View.OnClickListener {

    private val activity = this
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextRepeatPassword: TextInputEditText
    private lateinit var textViewLinkLogin: AppCompatTextView
    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_2registrar)

        // inicializar las vistas
        initViews()
        // inicializar los listeners
        initListeners()
        // inicializar los objetos
        initObjects()



        //------ESTILOS BOTONES--------
        btn_2Register.setOnTouchListener{  v, event ->
            btn_2Register.setBackgroundResource(R.drawable.my_button_border_click);
            Handler().postDelayed({
                btn_2Register.setBackgroundResource(R.drawable.my_button_border);
            }, 100)
            false
        }
        //------ESTILOS BOTONES FIN--------


    }

    private fun initViews() {
        textInputEditTextName = pText_2inputNombre as TextInputEditText
        textInputEditTextEmail = pText_2inputCorreo as TextInputEditText
        textInputEditTextPassword = pText_2inputContrasena as TextInputEditText
        textInputEditTextRepeatPassword = pText_2inputContrasenaV as TextInputEditText
        appCompatButtonRegister = btn_2Register as AppCompatButton
        textViewLinkLogin = txtv_2Login as AppCompatTextView
        constraintLayout = ctly_2layout as ConstraintLayout
    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        textViewLinkLogin!!.setOnClickListener(this)
        appCompatButtonRegister!!.setOnClickListener(this)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)
    }

    //Si el cliente le da a registrarse, creamos una función para llamar a la SQLite
    override fun onClick(v: View?) {
        when (v) {
            appCompatButtonRegister -> postDataToSQLite()
            textViewLinkLogin -> finish()
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {
        //Si el nombre está vacío, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName, getString(R.string.error_message_name))) {
            return
        }
        //Si el email está vacío, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, getString(R.string.error_message_email))) {
            return
        }
        //Si la contraseña está vacía, lanzar mensaje de error
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, getString(R.string.error_message_password))) {
            return
        }
        //Si el email está bien escrito (Con @...)
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, getString(R.string.error_message_email_correct))) {
            return
        }
        //Si las contraseñas no coinciden
        if (!inputValidation!!.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextRepeatPassword,
                getString(R.string.error_password_match))) {
            return
        }
        /*En caso de no estar vacías, llama a la base de datos para comprobar si existe o no el usuario
          e inserta un nuevo usuario*/
        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {
            var user = User(name = textInputEditTextName!!.text.toString().trim(),
                email = textInputEditTextEmail!!.text.toString().trim(),
                password = textInputEditTextPassword!!.text.toString().trim(),
                admin = 0 )

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(constraintLayout!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            databaseHelper!!.addUser(user)
            emptyInputEditText()
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(constraintLayout!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextName!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
        textInputEditTextRepeatPassword!!.text = null
    }

}