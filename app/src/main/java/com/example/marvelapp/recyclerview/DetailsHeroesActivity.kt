package com.example.marvelapp.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R

class DetailsHeroesActivity : AppCompatActivity() {
    val nameHerotxt by lazy { findViewById<TextView>(R.id.txt_name_heroi) }
    val imageHeroiv by lazy { findViewById<ImageView>(R.id.iv_imagem_heroi) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val information = intent.extras

        if(information!= null) {
            val nameHero = information.getString("NAME")

            val imageHero = information.getInt("IMAGE",0)

            val heroFound = Heroes(nameHero,
                    imageHero)

            nameHerotxt.text = heroFound.name
            imageHeroiv.setImageResource(imageHero)
        }else{
            Toast.makeText(this,"Error loading", Toast.LENGTH_LONG).show()
        }

    }
}