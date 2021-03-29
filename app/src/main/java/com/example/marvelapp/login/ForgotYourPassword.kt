package com.example.marvelappjean

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ForgotYourPassword: AppCompatActivity() {

    val buttonForgotYourPassword by lazy { findViewById<MaterialButton>(R.id.button_send_reset_link5) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_your_password)

        sendEmail()

    }

    private fun sendEmail() {
        buttonForgotYourPassword.setOnClickListener {
               CheckYourInbox().show(supportFragmentManager, "CheckYourInbox")
            }
        }
    }