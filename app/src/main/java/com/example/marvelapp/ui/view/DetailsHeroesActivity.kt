package com.example.marvelapp.ui.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.model.Result
import com.squareup.picasso.Picasso

class DetailsHeroesActivity : AppCompatActivity() {
    val heroNameTxt by lazy { findViewById<TextView>(R.id.txt_hero_name_details) }
    val heroImageViewIv by lazy { findViewById<ImageView>(R.id.iv_hero_image_details) }
    val heroDescription by lazy { findViewById<TextView>(R.id.txt_hero_synopsis_details) }
    val heroDate by lazy { findViewById<TextView>(R.id.txt_hero_date_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        val information = intent.extras

        if (information != null) {
            val hero = information.getSerializable("HEROES") as Result
            heroNameTxt.text = hero.name
            heroDescription.text = hero.description
            heroDate.text =  hero.modified
            Picasso.get().load(hero.thumbnail?.path + ".jpg").into(heroImageViewIv)

        } else {
            Toast.makeText(this, "Error loading", Toast.LENGTH_LONG).show()
        }

    }
}