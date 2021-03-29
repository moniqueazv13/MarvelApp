package com.example.marvelappjean

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AccountAlreadyRegistered:AppCompatActivity() {

    val buttonForgotPassword by lazy { findViewById<Button>(R.id.button_forgot_your_password3) }
    val buttonSignIn by lazy { findViewById<MaterialButton>(R.id.button_sign_in3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_already_registered)

        forgotPassword()

        signIn()

    }

    private fun forgotPassword() {
        buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotYourPassword::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        buttonSignIn.setOnClickListener {
            val intent = Intent(this, MainExample::class.java)
            startActivity(intent)
        }
    }
}