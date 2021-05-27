package com.example.marvelapp.presentation.home.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.Constants.POSTS_REFERENCE
import com.example.marvelapp.R
import com.example.marvelapp.model.ui.ComicsUi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comic_detail.*

class DetailsComicsActivity : AppCompatActivity() {

    private val myRef = Firebase.database.getReference(POSTS_REFERENCE)
    private lateinit var comic: ComicsUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val information = intent.extras

        if (information != null) {
            comic = information.getSerializable("COMICS") as ComicsUi
            checkFavorite()
            txt_comic_name_details.text = comic.title
            txt_synopsis_comic.text = comic.description
            comic.image?.let {
                Picasso.get().load(it).into(iv_comic_image_details)
            }
            favoriteButton.setOnClickListener {

                if (comic.favorite) {
                    myRef.child(comic.id.toString()).removeValue()
                        .addOnSuccessListener { showNotFavorite() }
                } else {
                    addPost()
                }
            }

        } else {
            Toast.makeText(this, "Error loading", Toast.LENGTH_LONG).show()
        }

    }

    private fun checkFavorite() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailsComicsActivity, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val favorite = postSnapshot.getValue(ComicsUi::class.java)
                    if (favorite?.id == comic.id) {
                        showFavorite()
                    }
                }
            }
        })
    }

    private fun addPost() {
        myRef.child(comic.id.toString())
            .setValue(comic)
            .addOnSuccessListener {
                Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
                showFavorite()
            }
            .addOnFailureListener { Toast.makeText(this, "Error", Toast.LENGTH_LONG).show() }
    }

    private fun showFavorite() {
        favoriteButton.background = getDrawable(R.drawable.ic_favorite_true)
        comic.favorite = true
    }

    private fun showNotFavorite() {
        favoriteButton.background = getDrawable(R.drawable.ic_favorite)
        comic.favorite = false
    }
}
