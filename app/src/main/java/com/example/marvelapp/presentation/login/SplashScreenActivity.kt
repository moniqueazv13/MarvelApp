package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val auth = FirebaseAuth.getInstance()
        val logged = auth.currentUser != null
        val user = auth.currentUser
        val intent = if (logged) {
            Intent(this, MainActivity::class.java)

        } else {
            Intent(this, LoginActivity::class.java)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
//                Toast.makeText(this, "Welcome ${user?.email ?: "Usuário desconectado"}" , Toast.LENGTH_LONG).show()
//                TOAST poderá ser alterado para o indicado abaixo se criar BD para armazenar nome do usuario criado no firebase
            Toast.makeText(this, "Welcome ${user?.displayName}", Toast.LENGTH_LONG).show()
            finish()
        }, 2000)
    }
}