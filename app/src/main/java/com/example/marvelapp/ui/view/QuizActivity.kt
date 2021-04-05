package com.example.marvelapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.FragmentConfirmRight
import com.example.marvelapp.FragmentHint
import com.example.marvelapp.R

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        
        initViews()
    }

    private fun initViews(){
        val imgNeg = findViewById<ImageView>(R.id.img_minus)
        val imgPos = findViewById<ImageView>(R.id.img_plus)

        imgNeg.setImageResource(R.drawable.icon_minus)
        imgPos.setImageResource(R.drawable.icon_plus)

        val imgHero = findViewById<ImageView>(R.id.img_hero_quiz)
        imgHero.clipToOutline = true

        val btHint = findViewById<Button>(R.id.bt_hint)
        val btConfirm = findViewById<Button>(R.id.bt_confirm)

        btHint.setOnClickListener {
            onShowFragment()
        }

        btConfirm.setOnClickListener {
            onShowFragmentRight()
        }
    }

    private fun onShowFragmentRight() {
        FragmentConfirmRight()
            .show(supportFragmentManager,"FragmentConfirmRight")
    }

    private fun onShowFragment() {
       FragmentHint().show(supportFragmentManager, "FragmentHint")
    }

}
