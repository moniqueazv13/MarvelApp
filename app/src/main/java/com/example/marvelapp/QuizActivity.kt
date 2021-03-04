package com.example.marvelapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val imgNeg = findViewById<ImageView>(R.id.img_minus)
        val imgPos = findViewById<ImageView>(R.id.img_plus)

        imgNeg.setImageResource(R.drawable.icon_minus)
        imgPos.setImageResource(R.drawable.icon_plus)
    }
}