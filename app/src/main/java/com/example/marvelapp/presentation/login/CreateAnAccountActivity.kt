package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class CreateAnAccountActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private val emailEt by lazy { findViewById<EditText>(R.id.et_name2) }
    private val passEt by lazy { findViewById<EditText>(R.id.et_password_user_creation) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_an_account)

        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
        val user = firebaseAuth.currentUser

        Toast.makeText(this, user?.email ?: "Usuário desconectado", Toast.LENGTH_LONG).show()
//        setUserEmail(currentUser?.email ?: "Usuário desconectado")
    }


    fun createUser(view: View) {
        val email = emailEt.text.toString()
        val pass = passEt.text.toString()

        createUserWithEmailPass(email, pass)
    }

    private fun createUserWithEmailPass(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Toast.makeText(this, user?.email ?: "Usuário desconectado", Toast.LENGTH_LONG).show()
//                setUserEmail(user?.email ?: "Usuário desconectado")
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, task.exception?.message!!, Toast.LENGTH_LONG).show()
//                setUserEmail(task.exception?.message!!)
            }
        }
    }

}
