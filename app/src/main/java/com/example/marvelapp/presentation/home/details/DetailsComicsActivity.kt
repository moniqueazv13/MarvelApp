package com.example.marvelapp.presentation.home.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.model.ui.ComicsUi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comic_detail.*

class DetailsComicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val information = intent.extras

        if (information != null) {
            val comic = information.getSerializable("COMICS") as ComicsUi
            txt_comic_name_details.text = comic.title
            txt_synopsis_comic.text = comic.description
            Picasso.get().load(comic.image?.path + ".jpg").into(iv_comic_image_details)

        } else {
            Toast.makeText(this, "Error loading", Toast.LENGTH_LONG).show()
        }


    }
}