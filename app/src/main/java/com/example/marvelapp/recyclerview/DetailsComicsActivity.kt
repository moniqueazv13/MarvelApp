package com.example.marvelapp.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R

class DetailsComicsActivity : AppCompatActivity() {
    val comicNametxt by lazy { findViewById<TextView>(R.id.txt_comic_name_details) }
    val comicImageiv by lazy { findViewById<ImageView>(R.id.iv_comic_image_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val information = intent.extras

        if(information!= null) {
            val comicName = information.getString("NAME")

            val comicImage = information.getInt("IMAGE",0)

            val comicFound = Comics(comicName,
                    comicImage)

            comicNametxt.text = comicFound.name
            comicImageiv.setImageResource(comicImage)
        }else{
            Toast.makeText(this,"Error loading", Toast.LENGTH_LONG).show()
        }

    }
}