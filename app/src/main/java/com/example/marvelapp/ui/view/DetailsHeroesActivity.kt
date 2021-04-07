package com.example.marvelapp.ui.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.model.Result

class DetailsHeroesActivity : AppCompatActivity() {
    val heroNametxt by lazy { findViewById<TextView>(R.id.txt_hero_name_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        val information = intent.extras

        if(information!= null) {
            val heroName = information.getString("NAME")

            val heroFound = Result(name = heroName)

            heroNametxt.text = heroFound.name
        }else{
            Toast.makeText(this,"Error loading", Toast.LENGTH_LONG).show()
        }

    }
}