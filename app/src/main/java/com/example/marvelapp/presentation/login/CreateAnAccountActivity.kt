package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_an_account.*

class CreateAnAccountActivity : AppCompatActivity() {

    private val emailField by lazy { findViewById<TextInputEditText>(R.id.et_email_new_user) }
    private val passField by lazy { findViewById<TextInputEditText>(R.id.et_password_user_creation) }
    private val confPassField by lazy { findViewById<TextInputEditText>(R.id.et_password_user_confirmation) }
    private val emailFieldLayout by lazy { findViewById<TextInputLayout>(R.id.til_email_new_user) }
    private val passFieldLayout by lazy { findViewById<TextInputLayout>(R.id.til_password_user_creation) }
    private val confPassFieldLayout by lazy { findViewById<TextInputLayout>(R.id.til_password_user_confirmation) }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: CreateAnAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_an_account)

        //quando passar pelo onCreate vamos pegar o CreateAnAccountViewModel e vai alocar ele na variavel viewmodel. Por isso, posso usar ela na minha classe inteira, possibilitando a mudança das lógicas para o viewmodel
        viewModel = ViewModelProvider(this).get(CreateAnAccountViewModel::class.java)

        configureValidationOfFields()

        firebaseAuth = FirebaseAuth.getInstance()
    }


    fun createUser(view: View) {
        onFinishForm()

    }

    private fun createUserWithEmailPass(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
            val user = firebaseAuth.currentUser
            Toast.makeText(
                this,
                "Welcome ${user?.email ?: "Usuário desconectado"}",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this, MainActivity::class.java))
        }.addOnFailureListener {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPassword(email: String, password: String, passConf: String) {
        if (password == passConf) {
            createUserWithEmailPass(email, password)
        } else {
            Toast.makeText(this, "Passwords must match", Toast.LENGTH_LONG).show()
        }
    }

    private fun onFinishForm() {

        val email = emailField?.text.toString()
        val password = passField?.text.toString()
        val confirmationPassword = confPassField?.text.toString()

        viewModel.validateEntryFields(email, password, confirmationPassword)
    }

    private fun configureValidationOfFields() {
        viewModel.fieldEmail.observe(this) { emailValid ->
            if (emailValid) {
                emailFieldLayout.error = null
            } else {
                emailFieldLayout.error = "Required"
            }

            navigateIfValid()
        }

        viewModel.fieldPassword.observe(this) { passValid ->
            if (passValid) {
                passFieldLayout.error = null
            } else {
                passFieldLayout.error = "Required"
            }

            navigateIfValid()
        }

        viewModel.fieldPasswordConfirmation.observe(this) { passConfValid ->
            if (passConfValid) {
                confPassFieldLayout.error = null
            } else {
                confPassFieldLayout.error = "Required"
            }

            navigateIfValid()
        }
    }

    private fun navigateIfValid() {
        if (emailFieldLayout.error.isNullOrBlank() &&
            passFieldLayout.error.isNullOrBlank() &&
            confPassFieldLayout.error.isNullOrBlank()
        ) {

            val email = emailField?.text.toString()
            val pass = passField?.text.toString()
            val passConfirmation = confPassField.text.toString()

            checkPassword(email, pass, passConfirmation)
        }
    }


}
