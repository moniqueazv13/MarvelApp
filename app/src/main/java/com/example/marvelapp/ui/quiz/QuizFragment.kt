package com.example.marvelapp.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.marvelapp.ui.view.QuizActivity
import com.example.marvelapp.R

class QuizFragment : Fragment(R.layout.fragment_quiz){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btStartGame = view.findViewById<Button>(R.id.bt_start)
        btStartGame.setOnClickListener {
            val intent = Intent(requireContext(), QuizActivity::class.java)
            startActivity(intent)
        }
    }
}


