package com.example.marvelapp.heroes

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.heroes.model.Heroes

class DetailsHeroesActivity : AppCompatActivity() {
    val heroNametxt by lazy { findViewById<TextView>(R.id.txt_hero_name_details) }
    val heroImageiv by lazy { findViewById<ImageView>(R.id.iv_hero_image_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        val information = intent.extras

        if(information!= null) {
            val heroName = information.getString("NAME")

            val heroImage = information.getInt("IMAGE",0)

            val heroFound = Heroes(heroName,
                    heroImage)

            heroNametxt.text = heroFound.name
            heroImageiv.setImageResource(heroImage)
        }else{
            Toast.makeText(this,"Error loading", Toast.LENGTH_LONG).show()
        }

    }
}