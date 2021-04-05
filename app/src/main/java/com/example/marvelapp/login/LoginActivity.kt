package com.example.marvelapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marvelapp.R
import com.example.marvelapp.ui.view.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private val buttonCreateAccount by lazy { findViewById<Button>(R.id.button_create_an_account1) }
    private val buttonForgotPassword by lazy { findViewById<Button>(R.id.button_forgot_your_password3) }
    private val buttonSignIn by lazy {findViewById<MaterialButton>(R.id.button_sign_in1)}
    private val nameField by lazy { findViewById<TextInputEditText>(R.id.tiet_email1) }
    private val passField by lazy { findViewById<TextInputEditText>(R.id.tiet_password1) }
    private val nameInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_email1) }
    private val passInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_password1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createAnAccount()

        forgotPassword()

        signIn()

    }


    private fun createAnAccount() {
        buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAnAccountActivity::class.java)
            startActivity(intent)
        }
    }


    private fun forgotPassword() {
        buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotYourPassword::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        buttonSignIn.setOnClickListener {
            onFinishForm()
        }
    }

    private fun onFinishForm(){

        val name = nameField?.text.toString()
        val pass = passField?.text.toString()

        when{
            name.isBlank() && pass.isBlank() -> {
                nameInputLayout?.error = "Required"
                passInputLayout?.error = "Required"
            }
            name.isBlank() -> {
                nameInputLayout?.error = "Required"
                passInputLayout?.error = null
            }
            pass.isBlank() -> {
                passInputLayout?.error = "Required"
                nameInputLayout?.error = null
            }
            else -> {
                nameInputLayout?.error = null
                passInputLayout?.error = null
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}