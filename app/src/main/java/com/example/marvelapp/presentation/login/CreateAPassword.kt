package com.example.marvelapp.presentation.login
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.marvelapp.R
//import com.example.marvelapp.presentation.MainActivity
//import com.google.android.material.button.MaterialButton
//
//class CreateAPassword: AppCompatActivity() {
//
//    val buttonSignIn by lazy { findViewById<MaterialButton>(R.id.button_sing_in5) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_create_a_password)
//
//        signIn()
//
//    }
//
//    private fun signIn() {
//        buttonSignIn.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}