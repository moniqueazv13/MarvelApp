package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_an_account.*

class CreateAnAccountActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_an_account)
    }


    fun createUser(view: View) {
        val email = et_email_new_user.text.toString()
        val pass = et_password_user_creation.text.toString()
        val passConfirmation = et_password_user_confirmation.text.toString()

        checkPassword(email, pass, passConfirmation)

    }

    private fun createUserWithEmailPass(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
            val user = firebaseAuth.currentUser
            Toast.makeText(this, "Welcome ${user?.email ?: "Usuário desconectado"}" , Toast.LENGTH_LONG).show()
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

}
