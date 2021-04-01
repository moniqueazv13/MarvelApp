package com.example.marvelapp.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.google.android.material.button.MaterialButton

class CreateAnAccountActivity: AppCompatActivity() {

    val buttonContinue by lazy { findViewById<MaterialButton>(R.id.button_sign_in1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_an_account)

    createAPassword()

    }

    private fun createAPassword (){
        buttonContinue.setOnClickListener{
            val intent = Intent(this, CreateAPassword::class.java)
            startActivity(intent)
        }
    }
}
